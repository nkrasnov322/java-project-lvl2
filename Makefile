install:
	./gradlew clean install
run-dist:
	./build/install/app/bin/app
check-updates:
	./gradlew dependencyUpdates
lint:
	./gradlew check
build:
	./gradlew clean build
	./gradlew compileJava
.PHONY: build