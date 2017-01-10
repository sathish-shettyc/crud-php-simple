build:
	docker build --no-cache -t upwork/mysql . 

run:
	docker rm -f some-mysql || true
	docker run -it -P --name some-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test -d  upwork/mysql
