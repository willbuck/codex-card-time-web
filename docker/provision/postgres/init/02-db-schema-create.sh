psql -v ON_ERROR_STOP=1 -U "$APP_DB_USER" -W "$APP_APP_DB_PASSWORD" -d $APP_DB_DATABASE <<-EOSQL

CREATE TABLE "user" (
                        "id" serial NOT NULL,
                        "username" varchar(255) UNIQUE,
                        "password" varchar(255),
                        "is_admin" BOOLEAN NOT NULL DEFAULT 'FALSE',
                        CONSTRAINT "user_pk" PRIMARY KEY ("id")
) WITH (
    OIDS=FALSE
);

-- password is 'todo_change_this'
INSERT INTO "user" ( username, password, is_admin) VALUES
    ('admin', '\$2a\$10\$ISI30Q4Ir5TqWY3R9UtKIut7ZiPGzcYgFcmhHZwguNsHST6FOwicG', true),
    ('codex_player', '\$2a\$10\$ISI30Q4Ir5TqWY3R9UtKIut7ZiPGzcYgFcmhHZwguNsHST6FOwicG', false)
EOSQL
