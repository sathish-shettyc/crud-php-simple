
run-registry:
	docker run -it -d -p 5000:5000 -v /data:/var/lib/registry --name registry registry:2

run-registry-proxy:
	docker run -it -d --restart always -p 5001:80 --link registry:registry -v ${PWD}/nginx/:/etc/nginx/conf.d:ro --name registry-proxy nginx:1.9

run:
	docker rm -f registry || true
	docker rm -f registry-proxy || true
	make run-registry
	make run-registry-proxy
