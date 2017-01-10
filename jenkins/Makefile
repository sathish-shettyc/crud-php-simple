build:
	docker build --no-cache -t upwork/jenkins .

run:
	docker rm -f jenkins || true
	docker run -it -P -d --name jenkins upwork/jenkins

run2:
	docker run -it -P -d --name jenkins2 upwork/jenkins
	
