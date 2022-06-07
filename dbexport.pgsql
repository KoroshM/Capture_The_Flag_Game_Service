--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: features; Type: TABLE; Schema: public; Owner: nayanayeshlur
--

CREATE TABLE public.features (
    feature_id integer,
    feature_name character varying(255)
);


ALTER TABLE public.features OWNER TO nayanayeshlur;

--
-- Name: flags; Type: TABLE; Schema: public; Owner: nayanayeshlur
--

CREATE TABLE public.flags (
    flag_id integer,
    flag_name character varying(255),
    feature1 character varying,
    feature2 character varying,
    feature3 character varying,
    fl_code integer,
    f2_code integer,
    f3_code integer,
    best_time_in_ms bigint
);


ALTER TABLE public.flags OWNER TO nayanayeshlur;

--
-- Name: player_information; Type: TABLE; Schema: public; Owner: nayanayeshlur
--

CREATE TABLE public.player_information (
    username character varying(255),
    password character varying(255),
    user_id integer,
    logged_in boolean
);


ALTER TABLE public.player_information OWNER TO nayanayeshlur;

--
-- Name: room_sessions; Type: TABLE; Schema: public; Owner: nayanayeshlur
--

CREATE TABLE public.room_sessions (
    session_id integer,
    started boolean,
    start_time bigint,
    current_flag character varying,
    f1 character varying,
    f1_code integer,
    f2 character varying,
    f2_code integer,
    f3 character varying,
    f3_code integer,
    num_players integer,
    p1_id integer,
    p2_id integer,
    p1_progress integer,
    p2_progress integer,
    winner_id integer,
    winner_time_in_ms bigint
);


ALTER TABLE public.room_sessions OWNER TO nayanayeshlur;

--
-- Data for Name: features; Type: TABLE DATA; Schema: public; Owner: nayanayeshlur
--

COPY public.features (feature_id, feature_name) FROM stdin;
0	White Stripe
2	Blue Stripe
1	Red Stripe
\.


--
-- Data for Name: flags; Type: TABLE DATA; Schema: public; Owner: nayanayeshlur
--

COPY public.flags (flag_id, flag_name, feature1, feature2, feature3, fl_code, f2_code, f3_code, best_time_in_ms) FROM stdin;
0	France	Red Stripe	White Stripe	Blue Stripe	1	0	2	-1
1	Russia	White Stripe	Blue Stripe	Red Stripe	0	2	1	32555
2	Netherlands	Red Stripe	White Stripe	Blue Stripe	1	0	2	123
\.


--
-- Data for Name: player_information; Type: TABLE DATA; Schema: public; Owner: nayanayeshlur
--

COPY public.player_information (username, password, user_id, logged_in) FROM stdin;
\.


--
-- Data for Name: room_sessions; Type: TABLE DATA; Schema: public; Owner: nayanayeshlur
--

COPY public.room_sessions (session_id, started, start_time, current_flag, f1, f1_code, f2, f2_code, f3, f3_code, num_players, p1_id, p2_id, p1_progress, p2_progress, winner_id, winner_time_in_ms) FROM stdin;
\.


--
-- Name: TABLE features; Type: ACL; Schema: public; Owner: nayanayeshlur
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.features TO game_service_db_user;


--
-- Name: TABLE flags; Type: ACL; Schema: public; Owner: nayanayeshlur
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.flags TO game_service_db_user;


--
-- Name: TABLE player_information; Type: ACL; Schema: public; Owner: nayanayeshlur
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.player_information TO game_service_db_user;


--
-- Name: TABLE room_sessions; Type: ACL; Schema: public; Owner: nayanayeshlur
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.room_sessions TO game_service_db_user;


--
-- PostgreSQL database dump complete
--

