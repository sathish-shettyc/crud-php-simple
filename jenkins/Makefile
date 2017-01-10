build:
	docker build --no-cache -t upwork/jenkins .

run:
	docker rm -f jenkins || true
	docker run -it -P -d --name jenkins_test upwork/jenkins

run2:
	docker rm -f jenkins2 || true
	docker run -it -P -d  -v /var/run/docker.sock:/var/run/docker.sock  -v $$(which docker):/bin/docker --name jenkins2 upwork/jenkins	
