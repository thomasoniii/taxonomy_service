use strict;
use Data::Dumper;
use Test::More;
use Config::Simple;
use Time::HiRes qw(time);
use Bio::KBase::AuthToken;
use Bio::KBase::workspace::Client;
use taxonomy_service::taxonomy_serviceImpl;

local $| = 1;
my $token = $ENV{'KB_AUTH_TOKEN'};
my $config_file = $ENV{'KB_DEPLOYMENT_CONFIG'};
my $config = new Config::Simple($config_file)->get_block('taxonomy_service');
my $ws_url = $config->{"workspace-url"};
my $ws_name = undef;
my $ws_client = new Bio::KBase::workspace::Client($ws_url,token => $token);
my $auth_token = Bio::KBase::AuthToken->new(token => $token, ignore_authrc => 1);
my $ctx = LocalCallContext->new($token, $auth_token->user_id);
$taxonomy_service::taxonomy_serviceServer::CallContext = $ctx;
my $impl = new taxonomy_service::taxonomy_serviceImpl();

sub get_ws_name {
    if (!defined($ws_name)) {
        my $suffix = int(time * 1000);
        $ws_name = 'test_taxonomy_service_' . $suffix;
        $ws_client->create_workspace({workspace => $ws_name});
    }
    return $ws_name;
}

my $dropdown ={
    private => 0,
    public => 1,
    search => 'Klebsiella ',
    limit => 10,
    start => 0

};
=head
ws_ref": "1292/406191/1",
        "parent_taxon_ref": "1292/146154/1"
        typical k.oxy substr M5a1 1292/505635/1
=cut

my $create_taxon_input = {
    scientific_name => "Klebsiella sp. janaka",
    parent => "1779/87821/1",
    genetic_code => "std_code",
    domain => "Bacteria",
    aliases => ["Klebsiella oxytoca str. janaka", "Klebsiella oxytoca strain janaka"]
};

eval {
   #my $ret =$impl->search_taxonomy($dropdown);
   my $ret =$impl->create_taxonomy($create_taxon_input);
};

my $err = undef;
if ($@) {
    $err = $@;
}
eval {
    if (defined($ws_name)) {
        $ws_client->delete_workspace({workspace => $ws_name});
        print("Test workspace was deleted\n");
    }
};
if (defined($err)) {
    if(ref($err) eq "Bio::KBase::Exceptions::KBaseException") {
        die("Error while running tests: " . $err->trace->as_string);
    } else {
        die $err;
    }
}

{
    package LocalCallContext;
    use strict;
    sub new {
        my($class,$token,$user) = @_;
        my $self = {
            token => $token,
            user_id => $user
        };
        return bless $self, $class;
    }
    sub user_id {
        my($self) = @_;
        return $self->{user_id};
    }
    sub token {
        my($self) = @_;
        return $self->{token};
    }
    sub provenance {
        my($self) = @_;
        return [{'service' => 'taxonomy_service', 'method' => 'please_never_use_it_in_production', 'method_params' => []}];
    }
    sub authenticated {
        return 1;
    }
    sub log_debug {
        my($self,$msg) = @_;
        print STDERR $msg."\n";
    }
    sub log_info {
        my($self,$msg) = @_;
        print STDERR $msg."\n";
    }
}
