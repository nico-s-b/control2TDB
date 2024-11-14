-- Table: public.students

-- DROP TABLE IF EXISTS public.students;

CREATE TABLE IF NOT EXISTS public.users
(
    userid integer NOT NULL DEFAULT nextval('users_userid_seq'::regclass),
    name character varying COLLATE pg_catalog."default",
    username character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (userid)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;


-- Table: public.tasks

-- DROP TABLE IF EXISTS public.tasks;

CREATE TABLE IF NOT EXISTS public.tasks
(
    taskid integer NOT NULL DEFAULT nextval('tasks_taskid_seq'::regclass),
    title character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    deadline date,
    status boolean,
    userid bigint,
    CONSTRAINT tasks_pkey PRIMARY KEY (taskid),
    CONSTRAINT userid FOREIGN KEY (userid)
        REFERENCES public.users (userid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tasks
    OWNER to postgres;