.PHONY: all Flippy Flipper Moteur IG clean

all: Flippy Flipper Moteur IG

Flippy:
	cd Code && mvn clean compile assembly:single
	cd Code && mvn test
	cp Code/IG/target/IG-0.1-jar-with-dependencies.jar Flippy.jar
	cd Code && mvn clean

Stats:
	mkdir Stats
	gitstats ./ ./Stats
	xdg-open Stats/index.html
