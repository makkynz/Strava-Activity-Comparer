
CREATE DATABASE "strava-custom";
GRANT ALL PRIVILEGES ON DATABASE "strava-custom" TO postgres;
\connect "strava-custom";



CREATE TABLE public.athletes
(
    id uuid NOT NULL,
    strava_id character varying COLLATE pg_catalog."default",
    first_name character varying COLLATE pg_catalog."default",
    last_name character varying COLLATE pg_catalog."default",
    city character varying COLLATE pg_catalog."default",
    profile_pic character varying COLLATE pg_catalog."default",
    access_token character varying COLLATE pg_catalog."default",
    refresh_token character varying COLLATE pg_catalog."default",
    date_created timestamp(6) with time zone,
    date_updated timestamp(6) with time zone,
    raw_strava_json character varying COLLATE pg_catalog."default",
    token_expiry timestamp(6) with time zone,
    activities_json character varying COLLATE pg_catalog."default",
    CONSTRAINT accounts_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.athletes
    OWNER to postgres;