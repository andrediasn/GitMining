compile: 
	mvn clean install

run: 
	java -jar target/GitMining-1.0-SNAPSHOT-jar-with-dependencies.jar "/home/andrediasn/Documentos/UFJF/Tópicos/Exercicio1" https://github.com/voldemort/voldemort

all: compile run



