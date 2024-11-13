-- Table: public.students

-- DROP TABLE IF EXISTS public.students;

CREATE TABLE IF NOT EXISTS public.students
(
    studentid integer NOT NULL DEFAULT nextval('students_studentid_seq'::regclass),
    username character varying[] COLLATE pg_catalog."default",
    password character varying[] COLLATE pg_catalog."default",
    CONSTRAINT students_pkey PRIMARY KEY (studentid)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.students
    OWNER to postgres;

-- Table: public.tasks

-- DROP TABLE IF EXISTS public.tasks;

CREATE TABLE IF NOT EXISTS public.tasks
(
    taskid integer NOT NULL DEFAULT nextval('tasks_taskid_seq'::regclass),
    titulo character varying[] COLLATE pg_catalog."default",
    descripcion character varying[] COLLATE pg_catalog."default",
    fecha_vencimiento timestamp without time zone,
    status boolean,
    studentid bigint,
    CONSTRAINT tasks_pkey PRIMARY KEY (taskid),
    CONSTRAINT studentid FOREIGN KEY (studentid)
        REFERENCES public.students (studentid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tasks
    OWNER to postgres;