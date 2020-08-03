CREATE TABLE public.history
(
  firstsum double precision NOT NULL,
  firstvalute character varying(200) COLLATE pg_catalog."default" NOT NULL,
  id bigint NOT NULL DEFAULT nextval('history_id_seq'::regclass),
  resultsum double precision NOT NULL,
  secondvalute character varying(200) COLLATE pg_catalog."default" NOT NULL,
  user_id integer NOT NULL,
  date character varying(200) COLLATE pg_catalog."default" NOT NULL,
  curs_id integer NOT NULL,
  CONSTRAINT history_pkey PRIMARY KEY (id),
  CONSTRAINT user_id_fk FOREIGN KEY (user_id)
  REFERENCES public.users (id) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT valcurs_id_fk FOREIGN KEY (curs_id)
  REFERENCES public.valcurs (id) MATCH SIMPLE
  ON UPDATE CASCADE
  ON DELETE CASCADE
)
TABLESPACE pg_default;

ALTER TABLE public.history
  OWNER to postgres;


CREATE TABLE public.users
(
  id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
  name character varying(200) COLLATE pg_catalog."default" NOT NULL,
  password character varying(254) COLLATE pg_catalog."default" NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.users
  OWNER to postgres;

CREATE TABLE public.valcurs
(
  id integer NOT NULL DEFAULT nextval('"ValCurs_id_seq"'::regclass),
  name character varying(200) COLLATE pg_catalog."default",
  date character varying(200) COLLATE pg_catalog."default" NOT NULL,
  CONSTRAINT "ValCurs_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.valcurs
  OWNER to postgres;

CREATE TABLE public.valutes
(
  id integer NOT NULL DEFAULT nextval('valutes_id_seq'::regclass),
  date_id integer,
  numcode integer NOT NULL,
  charcode character varying(200) COLLATE pg_catalog."default",
  nominal integer NOT NULL,
  name character varying(200) COLLATE pg_catalog."default" NOT NULL,
  value character varying(200) COLLATE pg_catalog."default" NOT NULL,
  valute_id character varying(200) COLLATE pg_catalog."default" NOT NULL,
  CONSTRAINT valutes_pkey PRIMARY KEY (id),
  CONSTRAINT valutes_valcurs_id_fk FOREIGN KEY (date_id)
  REFERENCES public.valcurs (id) MATCH SIMPLE
  ON UPDATE CASCADE
  ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.valutes
  OWNER to postgres;

CREATE UNIQUE INDEX valutes_id_uindex
  ON public.valutes USING btree
  (id ASC NULLS LAST)
TABLESPACE pg_default;