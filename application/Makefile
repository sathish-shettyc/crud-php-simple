build:
	docker build --no-cache -t nimmis/apache-php7	.
run:
	docker rm -vf php-app || true
	docker run -d -p 8080:80 -v ${PWD}/code:/var/www/html/ --link some-mysql:mysql --name php-app  nimmis/apache-php7
