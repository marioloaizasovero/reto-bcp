FROM mysql:8.0.17
ENV MYSQL_ROOT_PASSWORD: bcp
ENV MYSQL_DATABASE: bcp
ADD ./scripts/schema.sql /docker-entrypoint-initdb.d/1.sql
ADD ./scripts/data.sql /docker-entrypoint-initdb.d/2.sql

EXPOSE 3307:3306

