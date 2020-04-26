FROM postgres:9.5
MAINTAINER lpradel

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB strava-custom

ADD pg_hba.conf /etc/postgresql/9.5/main/
ADD postgresql.conf /etc/postgresql/9.5/main/
COPY init.sql /docker-entrypoint-initdb.d/