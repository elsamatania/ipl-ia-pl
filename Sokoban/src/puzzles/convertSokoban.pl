#!/usr/bin/perl -w
use strict;
use warnings;

sub converteFicheiro($){

	my $file = shift;
	open(FICH, $file) || die ("Erro ao abrir ficheiro: $!");

	my @conteudo = <FICH>;
	close(FICH);

	my $linha;

	foreach $linha (@conteudo){
		#chomp($linha);
		$linha =~ tr/#@*$. /PAXCOV/;
	}

	open(FICH, ">$file") || die ("Erro ao abrir ficheiro: $!");

	print FICH @conteudo;

	close(FICH);
}

my $dir = shift @ARGV;

opendir(DIR, $dir) || die ("Erro ao abrir diretoria: $!");
my @conteudo = readdir(DIR);
closedir(DIR);

my $file;

foreach $file (@conteudo){
	if($file ne "." && $file ne ".."){
		converteFicheiro($dir.'/'.$file);
		print "Ficheiro $file convertido!\n"
	}
}





