--
-- PostgreSQL database dump
--

-- Dumped from database version 16.8
-- Dumped by pg_dump version 16.8

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
-- Name: cuenta_contable; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cuenta_contable (
    id_cuenta_contable integer NOT NULL,
    codigo character varying(20) NOT NULL,
    nombre character varying(100) NOT NULL,
    id_padre integer
);


ALTER TABLE public.cuenta_contable OWNER TO postgres;

--
-- Name: cuenta_contable_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cuenta_contable_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cuenta_contable_id_seq OWNER TO postgres;

--
-- Name: cuenta_contable_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cuenta_contable_id_seq OWNED BY public.cuenta_contable.id_cuenta_contable;


--
-- Name: partida_diaria_detalle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.partida_diaria_detalle (
    id_partida_detalle integer NOT NULL,
    id_transaccion integer NOT NULL,
    id_cuenta_contable integer NOT NULL,
    id_tipo_saldo integer NOT NULL,
    monto numeric(12,2) NOT NULL
);


ALTER TABLE public.partida_diaria_detalle OWNER TO postgres;

--
-- Name: partida_diaria_detalle_id_partida_detalle_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.partida_diaria_detalle_id_partida_detalle_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.partida_diaria_detalle_id_partida_detalle_seq OWNER TO postgres;

--
-- Name: partida_diaria_detalle_id_partida_detalle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.partida_diaria_detalle_id_partida_detalle_seq OWNED BY public.partida_diaria_detalle.id_partida_detalle;


--
-- Name: tipo_saldo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_saldo (
    id_tipo_saldo integer NOT NULL,
    nombre character varying(10) NOT NULL
);


ALTER TABLE public.tipo_saldo OWNER TO postgres;

--
-- Name: tipo_saldo_id_tipo_saldo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_saldo_id_tipo_saldo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipo_saldo_id_tipo_saldo_seq OWNER TO postgres;

--
-- Name: tipo_saldo_id_tipo_saldo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_saldo_id_tipo_saldo_seq OWNED BY public.tipo_saldo.id_tipo_saldo;


--
-- Name: tipo_transaccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_transaccion (
    id_tipo_transaccion integer NOT NULL,
    nombre character varying(50) NOT NULL
);


ALTER TABLE public.tipo_transaccion OWNER TO postgres;

--
-- Name: tipo_transaccion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_transaccion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipo_transaccion_id_seq OWNER TO postgres;

--
-- Name: tipo_transaccion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_transaccion_id_seq OWNED BY public.tipo_transaccion.id_tipo_transaccion;


--
-- Name: transaccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaccion (
    id_transaccion integer NOT NULL,
    id_tipo_transaccion integer NOT NULL,
    fecha date NOT NULL,
    descripcion text
);


ALTER TABLE public.transaccion OWNER TO postgres;

--
-- Name: transaccion_id_transaccion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaccion_id_transaccion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.transaccion_id_transaccion_seq OWNER TO postgres;

--
-- Name: transaccion_id_transaccion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaccion_id_transaccion_seq OWNED BY public.transaccion.id_transaccion;


--
-- Name: cuenta_contable id_cuenta_contable; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta_contable ALTER COLUMN id_cuenta_contable SET DEFAULT nextval('public.cuenta_contable_id_seq'::regclass);


--
-- Name: partida_diaria_detalle id_partida_detalle; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partida_diaria_detalle ALTER COLUMN id_partida_detalle SET DEFAULT nextval('public.partida_diaria_detalle_id_partida_detalle_seq'::regclass);


--
-- Name: tipo_saldo id_tipo_saldo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_saldo ALTER COLUMN id_tipo_saldo SET DEFAULT nextval('public.tipo_saldo_id_tipo_saldo_seq'::regclass);


--
-- Name: tipo_transaccion id_tipo_transaccion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_transaccion ALTER COLUMN id_tipo_transaccion SET DEFAULT nextval('public.tipo_transaccion_id_seq'::regclass);


--
-- Name: transaccion id_transaccion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaccion ALTER COLUMN id_transaccion SET DEFAULT nextval('public.transaccion_id_transaccion_seq'::regclass);


--
-- Data for Name: cuenta_contable; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cuenta_contable VALUES (1, '1', 'Activo', NULL);
INSERT INTO public.cuenta_contable VALUES (2, '1.1', 'Activo corriente', 1);
INSERT INTO public.cuenta_contable VALUES (3, '1.101', 'EFECTIVO Y SUS EQUIVALENTES', 2);
INSERT INTO public.cuenta_contable VALUES (4, '1.102', 'CUENTAS Y DOCUMENTOS POR COBRAR', 2);
INSERT INTO public.cuenta_contable VALUES (5, '1.10101', 'Caja', 3);
INSERT INTO public.cuenta_contable VALUES (6, '1.10102', 'Caja chica', 3);
INSERT INTO public.cuenta_contable VALUES (7, '1.10103', 'Bancos', 3);
INSERT INTO public.cuenta_contable VALUES (8, '1.1010301', 'Agrícola', 7);
INSERT INTO public.cuenta_contable VALUES (9, '1.1010302', 'América Central', 7);
INSERT INTO public.cuenta_contable VALUES (10, '1.1010303', 'Davivienda', 7);
INSERT INTO public.cuenta_contable VALUES (11, '1.101030101', 'Cuenta corriente', 8);
INSERT INTO public.cuenta_contable VALUES (12, '1.101030102', 'Cuenta de ahorros', 8);
INSERT INTO public.cuenta_contable VALUES (13, '1.10201', 'Cuentas Por Cobrar', 4);
INSERT INTO public.cuenta_contable VALUES (14, '1.10202', 'Documentos Por Cobrar', 4);
INSERT INTO public.cuenta_contable VALUES (15, '1.10203', 'Anticipos a proveedores', 4);
INSERT INTO public.cuenta_contable VALUES (16, '1.10204', 'Préstamos y anticipos al personal', 4);
INSERT INTO public.cuenta_contable VALUES (17, '1.10205', 'Otras cuentas por cobrar', 4);
INSERT INTO public.cuenta_contable VALUES (18, '1.1020201', 'Clientes', 14);
INSERT INTO public.cuenta_contable VALUES (19, '1.1020202', 'Empleados', 14);
INSERT INTO public.cuenta_contable VALUES (20, '1.1020203', 'GOES', 14);
INSERT INTO public.cuenta_contable VALUES (21, '1.103', 'ESTIMACIÓN PARA CUENTAS INCOBRABLES®', 2);
INSERT INTO public.cuenta_contable VALUES (22, '1.1030201', 'Clientes', 21);
INSERT INTO public.cuenta_contable VALUES (23, '1.1030202', 'Empleados', 21);
INSERT INTO public.cuenta_contable VALUES (24, '1.1030203', 'Otros', 21);
INSERT INTO public.cuenta_contable VALUES (25, '1.104', 'IVA CRÉDITO FISCAL', 2);
INSERT INTO public.cuenta_contable VALUES (26, '1.10401', 'Por compras', 25);
INSERT INTO public.cuenta_contable VALUES (27, '1.10402', 'Retención IVA', 25);
INSERT INTO public.cuenta_contable VALUES (28, '1.10403', 'Remanente (Periodo anterior)', 25);
INSERT INTO public.cuenta_contable VALUES (29, '1.10404', 'Remanente (Próximo Periodo)', 25);
INSERT INTO public.cuenta_contable VALUES (30, '1.10405', 'IVA retenido a terceros', 25);
INSERT INTO public.cuenta_contable VALUES (45, '1.105', 'INVENTARIO', 2);
INSERT INTO public.cuenta_contable VALUES (46, '1.10501', 'Almacén de Materias Primas, Insumos y Materiales', 45);
INSERT INTO public.cuenta_contable VALUES (47, '1.10502', 'Almacén de Mercadería para la venta', 45);
INSERT INTO public.cuenta_contable VALUES (48, '1.10503', 'Almacén de productos terminados', 45);
INSERT INTO public.cuenta_contable VALUES (49, '1.10504', 'Almacén de productos en proceso', 45);
INSERT INTO public.cuenta_contable VALUES (50, '1.105041', 'Materia prima', 49);
INSERT INTO public.cuenta_contable VALUES (51, '1.105042', 'Mano de obra directa', 49);
INSERT INTO public.cuenta_contable VALUES (52, '1.10504101', 'Materia 1', 50);
INSERT INTO public.cuenta_contable VALUES (53, '1.10504102', 'Materia 2', 50);
INSERT INTO public.cuenta_contable VALUES (54, '1.10504103', 'Materia 3', 50);
INSERT INTO public.cuenta_contable VALUES (55, '1.10504104', 'Materia 4', 50);
INSERT INTO public.cuenta_contable VALUES (56, '1.10504105', 'Materia 5', 50);
INSERT INTO public.cuenta_contable VALUES (57, '1.10504106', 'Materia 6', 50);
INSERT INTO public.cuenta_contable VALUES (58, '1.10504107', 'Materia 7', 50);
INSERT INTO public.cuenta_contable VALUES (59, '1.10504108', 'Materia 8', 50);
INSERT INTO public.cuenta_contable VALUES (60, '1.10504109', 'Materia 9', 50);
INSERT INTO public.cuenta_contable VALUES (61, '1.10504110', 'Materia 10', 50);
INSERT INTO public.cuenta_contable VALUES (62, '1.10504111', 'Materia 11', 50);
INSERT INTO public.cuenta_contable VALUES (63, '1.10504112', 'Materia 12', 50);
INSERT INTO public.cuenta_contable VALUES (64, '1.10504113', 'Materia 13', 50);
INSERT INTO public.cuenta_contable VALUES (65, '1.10504114', 'Materia 14', 50);
INSERT INTO public.cuenta_contable VALUES (66, '1.10504115', 'Materia 15', 50);
INSERT INTO public.cuenta_contable VALUES (67, '1.10504116', 'Materia 16', 50);
INSERT INTO public.cuenta_contable VALUES (68, '1.10504201', 'Salarios', 51);
INSERT INTO public.cuenta_contable VALUES (69, '1.10504202', 'Sueldos', 51);
INSERT INTO public.cuenta_contable VALUES (70, '1.10504203', 'Horas extras', 51);
INSERT INTO public.cuenta_contable VALUES (71, '1.10504204', 'Comisiones y honorarios', 51);
INSERT INTO public.cuenta_contable VALUES (72, '1.10504205', 'Vacaciones', 51);
INSERT INTO public.cuenta_contable VALUES (73, '1.10504206', 'Aguinaldos', 51);
INSERT INTO public.cuenta_contable VALUES (74, '1.10504207', 'Agua', 51);
INSERT INTO public.cuenta_contable VALUES (75, '1.10504208', 'Luz', 51);
INSERT INTO public.cuenta_contable VALUES (76, '1.10504209', 'Teléfono', 51);
INSERT INTO public.cuenta_contable VALUES (77, '1.10504210', 'Combustibles y lubricantes', 51);
INSERT INTO public.cuenta_contable VALUES (78, '1.10504211', 'Depreciación', 51);
INSERT INTO public.cuenta_contable VALUES (79, '1.10504212', 'Viáticos', 51);
INSERT INTO public.cuenta_contable VALUES (80, '1.10504213', 'Alquiler', 51);
INSERT INTO public.cuenta_contable VALUES (81, '1.10504214', 'Indemnización', 51);
INSERT INTO public.cuenta_contable VALUES (82, '1.10504215', 'Papelería y útiles', 51);
INSERT INTO public.cuenta_contable VALUES (83, '1.10504216', 'ISSS', 51);
INSERT INTO public.cuenta_contable VALUES (84, '1.10504217', 'AFPs', 51);
INSERT INTO public.cuenta_contable VALUES (85, '1.10504218', 'Atenciones sociales', 51);
INSERT INTO public.cuenta_contable VALUES (86, '1.10504219', 'Seguros', 51);
INSERT INTO public.cuenta_contable VALUES (87, '1.10504220', 'Propaganda y publicidad', 51);
INSERT INTO public.cuenta_contable VALUES (88, '1.10504221', 'Incapacidades', 51);
INSERT INTO public.cuenta_contable VALUES (89, '1.10504222', 'Materia 17', 51);
INSERT INTO public.cuenta_contable VALUES (90, '1.10504223', 'Otros', 51);
INSERT INTO public.cuenta_contable VALUES (91, '1.105043', 'Costos Indirectos de Producción o Fabricación', 49);
INSERT INTO public.cuenta_contable VALUES (92, '1.10504301', 'Salarios', 91);
INSERT INTO public.cuenta_contable VALUES (93, '1.10504302', 'Sueldos', 91);
INSERT INTO public.cuenta_contable VALUES (94, '1.10504303', 'Horas extras', 91);
INSERT INTO public.cuenta_contable VALUES (95, '1.10504304', 'Comisiones y honorarios', 91);
INSERT INTO public.cuenta_contable VALUES (96, '1.10504305', 'Vacaciones', 91);
INSERT INTO public.cuenta_contable VALUES (97, '1.10504306', 'Aguinaldos', 91);
INSERT INTO public.cuenta_contable VALUES (98, '1.10504307', 'Agua', 91);
INSERT INTO public.cuenta_contable VALUES (99, '1.10504308', 'Luz', 91);
INSERT INTO public.cuenta_contable VALUES (100, '1.10504309', 'Teléfono', 91);
INSERT INTO public.cuenta_contable VALUES (101, '1.10504310', 'Combustibles y lubricantes', 91);
INSERT INTO public.cuenta_contable VALUES (102, '1.10504311', 'Depreciación', 91);
INSERT INTO public.cuenta_contable VALUES (103, '1.10504312', 'Viáticos', 91);
INSERT INTO public.cuenta_contable VALUES (104, '1.10504313', 'Alquiler', 91);
INSERT INTO public.cuenta_contable VALUES (105, '1.10504314', 'Indemnización', 91);
INSERT INTO public.cuenta_contable VALUES (106, '1.10504315', 'Papelería y útiles', 91);
INSERT INTO public.cuenta_contable VALUES (107, '1.10504316', 'ISSS', 91);
INSERT INTO public.cuenta_contable VALUES (108, '1.10504317', 'AFPs', 91);
INSERT INTO public.cuenta_contable VALUES (109, '1.10504318', 'Atenciones sociales', 91);
INSERT INTO public.cuenta_contable VALUES (110, '1.10504319', 'Seguros', 91);
INSERT INTO public.cuenta_contable VALUES (111, '1.10504320', 'Propaganda y publicidad', 91);
INSERT INTO public.cuenta_contable VALUES (112, '1.10504321', 'Incapacidades', 91);
INSERT INTO public.cuenta_contable VALUES (113, '1.10504322', 'Materia 1', 91);
INSERT INTO public.cuenta_contable VALUES (114, '1.10504323', 'Otros', 91);
INSERT INTO public.cuenta_contable VALUES (115, '1.106', 'ESTIMACIÓN PARA OBSOLESCENCIA DE INVENTARIO®', 45);
INSERT INTO public.cuenta_contable VALUES (116, '1.10601', 'Almacén de Materias Primas, Insumos y Materiales', 115);
INSERT INTO public.cuenta_contable VALUES (117, '1.10602', 'Almacén de Mercadería para la venta', 115);
INSERT INTO public.cuenta_contable VALUES (118, '1.10603', 'Almacén de productos terminados', 115);
INSERT INTO public.cuenta_contable VALUES (119, '1.10604', 'Almacén de productos en proceso', 115);
INSERT INTO public.cuenta_contable VALUES (120, '1.107', 'ACCIONISTAS', 2);
INSERT INTO public.cuenta_contable VALUES (121, '1.10701', 'Fundadores', 120);
INSERT INTO public.cuenta_contable VALUES (122, '1.10702', 'Nuevos Socios', 120);
INSERT INTO public.cuenta_contable VALUES (123, '1.10703', 'Materia 1', 120);
INSERT INTO public.cuenta_contable VALUES (124, '1.10704', 'Materia 2', 120);
INSERT INTO public.cuenta_contable VALUES (125, '1.108', 'GASTOS PAGADOS POR ANTICIPADO', 2);
INSERT INTO public.cuenta_contable VALUES (126, '1.10801', 'Seguros', 125);
INSERT INTO public.cuenta_contable VALUES (127, '1.10802', 'Alquileres', 125);
INSERT INTO public.cuenta_contable VALUES (128, '1.10803', 'Papelería y útiles', 125);
INSERT INTO public.cuenta_contable VALUES (129, '1.10804', 'Pago a cuenta Impuesto sobre la renta', 125);
INSERT INTO public.cuenta_contable VALUES (130, '1.2', 'ACTIVO NO CORRIENTE', 1);
INSERT INTO public.cuenta_contable VALUES (131, '1.201', 'TERRENOS', 130);
INSERT INTO public.cuenta_contable VALUES (132, '1.202', 'EDIFICIOS', 130);
INSERT INTO public.cuenta_contable VALUES (133, '1.203', 'MOBILIARIO Y EQUIPO', 130);
INSERT INTO public.cuenta_contable VALUES (134, '1.204', 'MAQUINARIA', 130);
INSERT INTO public.cuenta_contable VALUES (135, '1.205', 'OTROS ACTIVOS FIJOS', 130);
INSERT INTO public.cuenta_contable VALUES (136, '1.206', 'EQUIPO DE TRANSPORTE', 130);
INSERT INTO public.cuenta_contable VALUES (137, '1.207', 'OTROS ACTIVOS FIJOS', 130);
INSERT INTO public.cuenta_contable VALUES (138, '1.208', 'DEPRECIACIÓN ACUMULADA PROPIEDAD PLANTA Y EQUIPO PROPIOS AL COSTO', 130);
INSERT INTO public.cuenta_contable VALUES (139, '1.209', 'DEPRECIACIÓN DE REVALUÓ PROPIEDAD PLANTA Y EQUIPO PROPIOS', 130);
INSERT INTO public.cuenta_contable VALUES (140, '1.3', 'OTROS ACTIVOS', 1);
INSERT INTO public.cuenta_contable VALUES (141, '1.20301', 'Mobiliario y equipo de oficina', 133);
INSERT INTO public.cuenta_contable VALUES (142, '1.20302', 'Mobiliario y equipo de venta', 133);
INSERT INTO public.cuenta_contable VALUES (143, '1.20303', 'Equipos', 133);
INSERT INTO public.cuenta_contable VALUES (144, '1.20801', 'Depreciación de edificios', 138);
INSERT INTO public.cuenta_contable VALUES (145, '1.20802', 'Depreciación de mobiliario y equipo', 138);
INSERT INTO public.cuenta_contable VALUES (146, '1.20803', 'Depreciación de otros activos', 138);
INSERT INTO public.cuenta_contable VALUES (147, '1.20901', 'Depreciación de revaluó de edificio', 139);
INSERT INTO public.cuenta_contable VALUES (148, '1.20902', 'Depreciación de revaluó de mobiliario y equipo', 139);
INSERT INTO public.cuenta_contable VALUES (149, '1.20903', 'Depreciación de revaluó de maquinaria', 139);
INSERT INTO public.cuenta_contable VALUES (150, '1.20904', 'Depreciación de revaluó de otros activos', 139);
INSERT INTO public.cuenta_contable VALUES (151, '1.301', 'CUENTAS DIFERIDAS', 140);
INSERT INTO public.cuenta_contable VALUES (152, '1.30101', 'Gastos de Organización', NULL);
INSERT INTO public.cuenta_contable VALUES (153, '1.30102', 'Rentas Pagadas por Anticipado', NULL);
INSERT INTO public.cuenta_contable VALUES (154, '1.30103', 'Intereses Pagados por Anticipado', NULL);
INSERT INTO public.cuenta_contable VALUES (155, '1.30104', 'Seguros Pagados por Anticipado', NULL);
INSERT INTO public.cuenta_contable VALUES (156, '1.302', 'CUENTAS TRANSITORIAS', 140);
INSERT INTO public.cuenta_contable VALUES (157, '1.30201', 'Random 1', NULL);
INSERT INTO public.cuenta_contable VALUES (158, '1.30202', 'Random 2', NULL);
INSERT INTO public.cuenta_contable VALUES (159, '2', 'PASIVO', NULL);
INSERT INTO public.cuenta_contable VALUES (160, '2.1', 'PASIVO CORRIENTE', 159);
INSERT INTO public.cuenta_contable VALUES (161, '2.101', 'PRESTAMOS Y SOBRE GIROS BANCARIOS', 160);
INSERT INTO public.cuenta_contable VALUES (162, '2.102', 'CUENTAS Y DOCUMENTOS POR PAGAR', 160);
INSERT INTO public.cuenta_contable VALUES (163, '2.103', 'REMUNERACIONES Y PRESTACIONES POR PAGAR A CORTO PLAZO A EMPLEADOS', 160);
INSERT INTO public.cuenta_contable VALUES (164, '2.104', 'ACREEDORES VARIOS Y PROVISIONES', 160);
INSERT INTO public.cuenta_contable VALUES (165, '2.105', 'Retenciones y descuentos', 160);
INSERT INTO public.cuenta_contable VALUES (166, '2.106', 'IVA DEBITO FISCAL', 160);
INSERT INTO public.cuenta_contable VALUES (167, '2.107', 'Impuesto por pagar', 160);
INSERT INTO public.cuenta_contable VALUES (168, '2.10101', 'Sobre giros bancarios', 161);
INSERT INTO public.cuenta_contable VALUES (169, '2.10102', 'Préstamos a corto plazo', 161);
INSERT INTO public.cuenta_contable VALUES (170, '2.10103', 'Porción circulante de préstamos a largo plazo', 161);
INSERT INTO public.cuenta_contable VALUES (171, '2.10104', 'Otros prestamos', 161);
INSERT INTO public.cuenta_contable VALUES (172, '2.10201', 'Cuentas por Pagar', 162);
INSERT INTO public.cuenta_contable VALUES (173, '2.10202', 'Documentos por pagar', 162);
INSERT INTO public.cuenta_contable VALUES (174, '2.10203', 'Cartas de crédito', 162);
INSERT INTO public.cuenta_contable VALUES (175, '2.10204', 'Pagares', 162);
INSERT INTO public.cuenta_contable VALUES (176, '2.10205', 'Letras de cambio', 162);
INSERT INTO public.cuenta_contable VALUES (177, '2.10301', 'Salarios', 163);
INSERT INTO public.cuenta_contable VALUES (178, '2.10302', 'Comisiones', 163);
INSERT INTO public.cuenta_contable VALUES (179, '2.10303', 'Bonificaciones', 163);
INSERT INTO public.cuenta_contable VALUES (180, '2.10304', 'Vacaciones', 163);
INSERT INTO public.cuenta_contable VALUES (181, '2.10305', 'Aguinaldos', 163);
INSERT INTO public.cuenta_contable VALUES (182, '2.10401', 'Acreedores locales', 164);
INSERT INTO public.cuenta_contable VALUES (183, '2.10402', 'Cuota patronal ISSS salud', 164);
INSERT INTO public.cuenta_contable VALUES (184, '2.10403', 'Cuota patronal AFP', 164);
INSERT INTO public.cuenta_contable VALUES (185, '2.10404', 'ANDA', 164);
INSERT INTO public.cuenta_contable VALUES (186, '2.10405', 'CLESA', 164);
INSERT INTO public.cuenta_contable VALUES (187, '2.10406', 'Intereses por pagar', 164);
INSERT INTO public.cuenta_contable VALUES (188, '2.10501', 'Cotizaciones al ISSS', 165);
INSERT INTO public.cuenta_contable VALUES (189, '2.10502', 'Cotizaciones al AFP', 165);
INSERT INTO public.cuenta_contable VALUES (190, '2.10503', 'Retenciones del Impuesto Sobre la Renta', 165);
INSERT INTO public.cuenta_contable VALUES (191, '2.10504', 'IVA retenido a terceros', 165);
INSERT INTO public.cuenta_contable VALUES (192, '2.10505', 'Otras retenciones', 165);
INSERT INTO public.cuenta_contable VALUES (193, '2.10601', 'Por ventas a consumidores', 166);
INSERT INTO public.cuenta_contable VALUES (194, '2.10602', 'Por ventas a contribuyentes', 166);
INSERT INTO public.cuenta_contable VALUES (195, '2.10603', 'Percepción IVA', 166);
INSERT INTO public.cuenta_contable VALUES (196, '2.10701', 'Impuesto sobre la renta', 167);
INSERT INTO public.cuenta_contable VALUES (197, '2.10702', 'IVA por pagar', 167);
INSERT INTO public.cuenta_contable VALUES (198, '2.10703', 'Impuestos municipales', 167);
INSERT INTO public.cuenta_contable VALUES (199, '2.10704', 'Provisión pago a cuenta', 167);
INSERT INTO public.cuenta_contable VALUES (200, '2.2', 'PASIVOS NO CORRIENTES', 159);
INSERT INTO public.cuenta_contable VALUES (201, '2.201', 'Préstamos bancarios a largo plazo', 200);
INSERT INTO public.cuenta_contable VALUES (202, '2.202', 'Otros préstamos a largo plazo', 200);
INSERT INTO public.cuenta_contable VALUES (203, '2.203', 'Ingresos anticipados de los clientes', 200);
INSERT INTO public.cuenta_contable VALUES (204, '2.204', 'Provisión para obligaciones laborales', 200);
INSERT INTO public.cuenta_contable VALUES (205, '2.205', 'Otros pasivos no corrientes', 200);
INSERT INTO public.cuenta_contable VALUES (206, '2.3', 'OTROS ACTIVOS', 159);
INSERT INTO public.cuenta_contable VALUES (207, '2.301', 'CUENTAS DIFERIDAS', 206);
INSERT INTO public.cuenta_contable VALUES (212, '2.302', 'CUENTAS TRANSITORIAS', 206);
INSERT INTO public.cuenta_contable VALUES (216, '3.1', 'CAPITAL CONTABLE', 215);
INSERT INTO public.cuenta_contable VALUES (217, '3.101', 'CAPITAL', 216);
INSERT INTO public.cuenta_contable VALUES (221, '3.102', 'Superávit por reevaluaciones', 216);
INSERT INTO public.cuenta_contable VALUES (226, '3.103', 'Utilidades no distribuidas', 216);
INSERT INTO public.cuenta_contable VALUES (230, '4.1', 'COSTOS', 229);
INSERT INTO public.cuenta_contable VALUES (231, '4.101', 'COSTO DE VENTA', 230);
INSERT INTO public.cuenta_contable VALUES (235, '4.102', 'COSTO DE PRODUCCIÓN', 230);
INSERT INTO public.cuenta_contable VALUES (236, '4.10201', 'Materia prima', 235);
INSERT INTO public.cuenta_contable VALUES (213, '2.30201', 'Sra Random 1', 212);
INSERT INTO public.cuenta_contable VALUES (214, '2.30202', 'Sr Random 2', 212);
INSERT INTO public.cuenta_contable VALUES (215, '3', 'PATRIMONIO', 212);
INSERT INTO public.cuenta_contable VALUES (218, '3.10101', 'Sr Random 3', 217);
INSERT INTO public.cuenta_contable VALUES (219, '3.10102', 'Sra Random 2', 217);
INSERT INTO public.cuenta_contable VALUES (253, '4.10202', 'Mano de obra directa', 235);
INSERT INTO public.cuenta_contable VALUES (277, '4.10203', 'Costos Indirectos de Producción o Fabricación', 235);
INSERT INTO public.cuenta_contable VALUES (301, '4.2', 'GASTOS', 229);
INSERT INTO public.cuenta_contable VALUES (302, '4.201', 'GASTOS DE VENTA Y DISTRIBUCIÓN', 301);
INSERT INTO public.cuenta_contable VALUES (326, '4.202', 'GASTOS DE ADMINISTRACIÓN', 301);
INSERT INTO public.cuenta_contable VALUES (356, '4.203', 'GASTOS FINANCIEROS', 301);
INSERT INTO public.cuenta_contable VALUES (365, '4.204', 'GASTOS POR IMPUESTO SOBRE LA RENTA', 301);
INSERT INTO public.cuenta_contable VALUES (370, '5.1', 'INGRESOS', 369);
INSERT INTO public.cuenta_contable VALUES (371, '5.101', 'VENTAS', 370);
INSERT INTO public.cuenta_contable VALUES (375, '5.102', 'Ingresos financieros', 370);
INSERT INTO public.cuenta_contable VALUES (379, '52', 'INGRESOS DE NO OPERACIÓN', 369);
INSERT INTO public.cuenta_contable VALUES (380, '5201', 'DIVIDENDOS GANADOS', 379);
INSERT INTO public.cuenta_contable VALUES (381, '5202', 'OTROS INGRESOS NO OPERACIONALES', 379);
INSERT INTO public.cuenta_contable VALUES (260, '4.1020207', 'Agua', 253);
INSERT INTO public.cuenta_contable VALUES (383, '5203', 'INGRESOS EXTRAORDINARIOS', 379);
INSERT INTO public.cuenta_contable VALUES (387, '6', 'CUENTA LIQUIDADORA', NULL);
INSERT INTO public.cuenta_contable VALUES (388, '6.1', 'CUENTA CIERRE', 387);
INSERT INTO public.cuenta_contable VALUES (393, '7.1', 'CUENTAS DE ORDEN DEUDORAS', 392);
INSERT INTO public.cuenta_contable VALUES (401, '7.2', 'CUENTAS DE ORDEN ACREEDORAS', 392);
INSERT INTO public.cuenta_contable VALUES (208, '2.30101', 'Rentas Cobradas por Anticipado', 207);
INSERT INTO public.cuenta_contable VALUES (209, '2.30102', 'Intereses Cobrados por Anticipado', 207);
INSERT INTO public.cuenta_contable VALUES (210, '2.30103', 'Seguros Cobrados por Anticipado', 207);
INSERT INTO public.cuenta_contable VALUES (211, '2.30104', 'Sr Random 1', 207);
INSERT INTO public.cuenta_contable VALUES (220, '3.10103', 'Sr Random 4', 217);
INSERT INTO public.cuenta_contable VALUES (222, '3.10201', 'Terrenos', 221);
INSERT INTO public.cuenta_contable VALUES (223, '3.10202', 'Mobiliario y equipo', 221);
INSERT INTO public.cuenta_contable VALUES (224, '3.10203', 'Maquinaria', 221);
INSERT INTO public.cuenta_contable VALUES (225, '3.10204', 'Otros activos', 221);
INSERT INTO public.cuenta_contable VALUES (227, '3.10301', 'De ejercicios anteriores', 226);
INSERT INTO public.cuenta_contable VALUES (228, '3.10302', 'Del presente ejercicio', 226);
INSERT INTO public.cuenta_contable VALUES (229, '4', 'CUENTAS DE RESULTADO DEUDORAS', NULL);
INSERT INTO public.cuenta_contable VALUES (232, '4.10101', 'Mercadería', 231);
INSERT INTO public.cuenta_contable VALUES (233, '4.10102', 'Servicios', 231);
INSERT INTO public.cuenta_contable VALUES (234, '4.10103', 'Productos Terminados', 231);
INSERT INTO public.cuenta_contable VALUES (237, '4.1020101', 'Random 1', 236);
INSERT INTO public.cuenta_contable VALUES (238, '4.1020102', 'Random 2', 236);
INSERT INTO public.cuenta_contable VALUES (239, '4.1020103', 'Random 3', 236);
INSERT INTO public.cuenta_contable VALUES (240, '4.1020104', 'Random 4', 236);
INSERT INTO public.cuenta_contable VALUES (241, '4.1020105', 'Random 5', 236);
INSERT INTO public.cuenta_contable VALUES (242, '4.1020106', 'Random 6', 236);
INSERT INTO public.cuenta_contable VALUES (243, '4.1020107', 'Random 7', 236);
INSERT INTO public.cuenta_contable VALUES (244, '4.1020108', 'Random 8', 236);
INSERT INTO public.cuenta_contable VALUES (245, '4.1020109', 'Random 9', 236);
INSERT INTO public.cuenta_contable VALUES (246, '4.1020110', 'Random 10', 236);
INSERT INTO public.cuenta_contable VALUES (247, '4.1020111', 'Random 11', 236);
INSERT INTO public.cuenta_contable VALUES (248, '4.1020112', 'Random 12', 236);
INSERT INTO public.cuenta_contable VALUES (249, '4.1020113', 'Random 13', 236);
INSERT INTO public.cuenta_contable VALUES (250, '4.1020114', 'Random 14', 236);
INSERT INTO public.cuenta_contable VALUES (251, '4.1020115', 'Random 15', 236);
INSERT INTO public.cuenta_contable VALUES (252, '4.1020116', 'Random 16', 236);
INSERT INTO public.cuenta_contable VALUES (254, '4.1020201', 'Salarios', 253);
INSERT INTO public.cuenta_contable VALUES (255, '4.1020202', 'Sueldos', 253);
INSERT INTO public.cuenta_contable VALUES (256, '4.1020203', 'Horas extras', 253);
INSERT INTO public.cuenta_contable VALUES (257, '4.1020204', 'Comisiones y honorarios', 253);
INSERT INTO public.cuenta_contable VALUES (258, '4.1020205', 'Vacaciones', 253);
INSERT INTO public.cuenta_contable VALUES (259, '4.1020206', 'Aguinaldos', 253);
INSERT INTO public.cuenta_contable VALUES (261, '4.1020208', 'Luz', 253);
INSERT INTO public.cuenta_contable VALUES (262, '4.1020209', 'Teléfono', 253);
INSERT INTO public.cuenta_contable VALUES (263, '4.1020210', 'Combustibles y lubricantes', 253);
INSERT INTO public.cuenta_contable VALUES (264, '4.1020211', 'Depreciación', 253);
INSERT INTO public.cuenta_contable VALUES (265, '4.1020212', 'Viáticos', 253);
INSERT INTO public.cuenta_contable VALUES (266, '4.1020213', 'Alquiler', 253);
INSERT INTO public.cuenta_contable VALUES (267, '4.1020214', 'Indemnización', 253);
INSERT INTO public.cuenta_contable VALUES (268, '4.1020215', 'Papelería y útiles', 253);
INSERT INTO public.cuenta_contable VALUES (269, '4.1020216', 'ISSS', 253);
INSERT INTO public.cuenta_contable VALUES (270, '4.1020217', 'AFPs', 253);
INSERT INTO public.cuenta_contable VALUES (271, '4.1020218', 'Atenciones sociales', 253);
INSERT INTO public.cuenta_contable VALUES (272, '4.1020219', 'Seguros', 253);
INSERT INTO public.cuenta_contable VALUES (273, '4.1020220', 'Propaganda y publicidad', 253);
INSERT INTO public.cuenta_contable VALUES (274, '4.1020221', 'Incapacidades', 253);
INSERT INTO public.cuenta_contable VALUES (275, '4.1020222', 'Random 17', 253);
INSERT INTO public.cuenta_contable VALUES (276, '4.1020223', 'Otros', 253);
INSERT INTO public.cuenta_contable VALUES (278, '4.1020301', 'Salarios', 277);
INSERT INTO public.cuenta_contable VALUES (279, '4.1020302', 'Sueldos', 277);
INSERT INTO public.cuenta_contable VALUES (280, '4.1020303', 'Horas extras', 277);
INSERT INTO public.cuenta_contable VALUES (281, '4.1020304', 'Comisiones y honorarios', 277);
INSERT INTO public.cuenta_contable VALUES (282, '4.1020305', 'Vacaciones', 277);
INSERT INTO public.cuenta_contable VALUES (283, '4.1020306', 'Aguinaldos', 277);
INSERT INTO public.cuenta_contable VALUES (284, '4.1020307', 'Agua', 277);
INSERT INTO public.cuenta_contable VALUES (285, '4.1020308', 'Luz', 277);
INSERT INTO public.cuenta_contable VALUES (286, '4.1020309', 'Teléfono', 277);
INSERT INTO public.cuenta_contable VALUES (287, '4.1020310', 'Combustibles y lubricantes', 277);
INSERT INTO public.cuenta_contable VALUES (288, '4.1020311', 'Depreciación', 277);
INSERT INTO public.cuenta_contable VALUES (289, '4.1020312', 'Viáticos', 277);
INSERT INTO public.cuenta_contable VALUES (290, '4.1020313', 'Alquiler', 277);
INSERT INTO public.cuenta_contable VALUES (291, '4.1020314', 'Indemnización', 277);
INSERT INTO public.cuenta_contable VALUES (292, '4.1020315', 'Papelería y útiles', 277);
INSERT INTO public.cuenta_contable VALUES (293, '4.1020316', 'ISSS', 277);
INSERT INTO public.cuenta_contable VALUES (294, '4.1020317', 'AFPs', 277);
INSERT INTO public.cuenta_contable VALUES (295, '4.1020318', 'Atenciones sociales', 277);
INSERT INTO public.cuenta_contable VALUES (296, '4.1020319', 'Seguros', 277);
INSERT INTO public.cuenta_contable VALUES (297, '4.1020320', 'Propaganda y publicidad', 277);
INSERT INTO public.cuenta_contable VALUES (298, '4.1020321', 'Incapacidades', 277);
INSERT INTO public.cuenta_contable VALUES (299, '4.1020322', 'Random 18', 277);
INSERT INTO public.cuenta_contable VALUES (300, '4.1020323', 'Otros', 277);
INSERT INTO public.cuenta_contable VALUES (303, '4.20101', 'Salarios', 302);
INSERT INTO public.cuenta_contable VALUES (304, '4.20102', 'Sueldos', 302);
INSERT INTO public.cuenta_contable VALUES (305, '4.20103', 'Horas extras', 302);
INSERT INTO public.cuenta_contable VALUES (306, '4.20104', 'Comisiones y honorarios', 302);
INSERT INTO public.cuenta_contable VALUES (307, '4.20105', 'Vacaciones', 302);
INSERT INTO public.cuenta_contable VALUES (308, '4.20106', 'Aguinaldos', 302);
INSERT INTO public.cuenta_contable VALUES (309, '4.20107', 'Agua', 302);
INSERT INTO public.cuenta_contable VALUES (310, '4.20108', 'Luz', 302);
INSERT INTO public.cuenta_contable VALUES (311, '4.20109', 'Teléfono', 302);
INSERT INTO public.cuenta_contable VALUES (312, '4.20110', 'Combustibles y lubricantes', 302);
INSERT INTO public.cuenta_contable VALUES (313, '4.20111', 'Depreciación', 302);
INSERT INTO public.cuenta_contable VALUES (314, '4.20112', 'Viáticos', 302);
INSERT INTO public.cuenta_contable VALUES (315, '4.20113', 'Alquiler', 302);
INSERT INTO public.cuenta_contable VALUES (316, '4.20114', 'Indemnización', 302);
INSERT INTO public.cuenta_contable VALUES (317, '4.20115', 'Papelería y útiles', 302);
INSERT INTO public.cuenta_contable VALUES (318, '4.20116', 'ISSS', 302);
INSERT INTO public.cuenta_contable VALUES (319, '4.20117', 'AFPs', 302);
INSERT INTO public.cuenta_contable VALUES (320, '4.20118', 'Atenciones sociales', 302);
INSERT INTO public.cuenta_contable VALUES (321, '4.20119', 'Seguros', 302);
INSERT INTO public.cuenta_contable VALUES (322, '4.20120', 'Propaganda y publicidad', 302);
INSERT INTO public.cuenta_contable VALUES (323, '4.20121', 'Incapacidades', 302);
INSERT INTO public.cuenta_contable VALUES (324, '4.20122', 'Random 1', 302);
INSERT INTO public.cuenta_contable VALUES (325, '4.20123', 'Otros', 302);
INSERT INTO public.cuenta_contable VALUES (327, '4.20201', 'Salarios', 326);
INSERT INTO public.cuenta_contable VALUES (328, '4.20202', 'Sueldos', 326);
INSERT INTO public.cuenta_contable VALUES (392, '7', 'CUENTAS DE ORDEN', NULL);
INSERT INTO public.cuenta_contable VALUES (329, '4.20203', 'Horas extras', 326);
INSERT INTO public.cuenta_contable VALUES (330, '4.20204', 'Comisiones y honorarios', 326);
INSERT INTO public.cuenta_contable VALUES (331, '4.20205', 'Vacaciones', 326);
INSERT INTO public.cuenta_contable VALUES (332, '4.20206', 'Aguinaldos', 326);
INSERT INTO public.cuenta_contable VALUES (333, '4.20207', 'Agua', 326);
INSERT INTO public.cuenta_contable VALUES (334, '4.20208', 'Luz', 326);
INSERT INTO public.cuenta_contable VALUES (335, '4.20209', 'Teléfono', 326);
INSERT INTO public.cuenta_contable VALUES (336, '4.20210', 'Combustibles y lubricantes', 326);
INSERT INTO public.cuenta_contable VALUES (337, '4.20211', 'Depreciación', 326);
INSERT INTO public.cuenta_contable VALUES (338, '4.20212', 'Viáticos', 326);
INSERT INTO public.cuenta_contable VALUES (339, '4.20213', 'Alquiler', 326);
INSERT INTO public.cuenta_contable VALUES (340, '4.20214', 'Indemnización', 326);
INSERT INTO public.cuenta_contable VALUES (341, '4.20215', 'Papelería y útiles', 326);
INSERT INTO public.cuenta_contable VALUES (342, '4.20216', 'ISSS', 326);
INSERT INTO public.cuenta_contable VALUES (343, '4.20217', 'AFPs', 326);
INSERT INTO public.cuenta_contable VALUES (344, '4.20218', 'Atenciones sociales', 326);
INSERT INTO public.cuenta_contable VALUES (345, '4.20219', 'Seguros', 326);
INSERT INTO public.cuenta_contable VALUES (346, '4.20220', 'Propaganda y publicidad', 326);
INSERT INTO public.cuenta_contable VALUES (347, '4.20221', 'Incapacidades', 326);
INSERT INTO public.cuenta_contable VALUES (348, '4.20222', 'Matricula de comercio', 326);
INSERT INTO public.cuenta_contable VALUES (349, '4.20223', 'Impuestos', 326);
INSERT INTO public.cuenta_contable VALUES (350, '4.20224', 'Dietas', 326);
INSERT INTO public.cuenta_contable VALUES (351, '4.20225', 'Random 2', 326);
INSERT INTO public.cuenta_contable VALUES (352, '4.20226', 'Random 3', 326);
INSERT INTO public.cuenta_contable VALUES (353, '4.20227', 'Random 4', 326);
INSERT INTO public.cuenta_contable VALUES (354, '4.20228', 'Random 5', 326);
INSERT INTO public.cuenta_contable VALUES (355, '4.20229', 'Otros', 326);
INSERT INTO public.cuenta_contable VALUES (357, '4.20301', 'Intereses', 356);
INSERT INTO public.cuenta_contable VALUES (358, '4.20302', 'Honorarios profesionales', 356);
INSERT INTO public.cuenta_contable VALUES (359, '4.20303', 'Comisiones bancarias', 356);
INSERT INTO public.cuenta_contable VALUES (360, '4.20304', 'Papelería y útiles', 356);
INSERT INTO public.cuenta_contable VALUES (361, '4.20305', 'Diferenciales bancarios', 356);
INSERT INTO public.cuenta_contable VALUES (362, '4.20306', 'Gastos por activos recibidos en arrendamiento', 356);
INSERT INTO public.cuenta_contable VALUES (363, '4.20307', 'Perdida en venta o retiro de activo', 356);
INSERT INTO public.cuenta_contable VALUES (364, '4.20308', 'Gastos por deterioro de activos', 356);
INSERT INTO public.cuenta_contable VALUES (366, '4.20401', 'Impuesto Sobre la Renta corriente', 365);
INSERT INTO public.cuenta_contable VALUES (367, '4.20402', 'Impuesto Sobre la Renta diferido-activo', 365);
INSERT INTO public.cuenta_contable VALUES (368, '4.20403', 'Impuesto Sobre la Renta diferido-pasivo', 365);
INSERT INTO public.cuenta_contable VALUES (372, '5.10101', 'Mercaderías', 371);
INSERT INTO public.cuenta_contable VALUES (373, '5.10102', 'Productos Terminados', 371);
INSERT INTO public.cuenta_contable VALUES (374, '5.10103', 'Servicios', 371);
INSERT INTO public.cuenta_contable VALUES (376, '5.10201', 'Intereses bancarios', 375);
INSERT INTO public.cuenta_contable VALUES (377, '5.10202', 'Comisiones', 375);
INSERT INTO public.cuenta_contable VALUES (378, '5.10203', 'Diferenciales bancarios', 375);
INSERT INTO public.cuenta_contable VALUES (382, '520201', 'Ingresos por Activos dados en Arrendamiento Financiero', 381);
INSERT INTO public.cuenta_contable VALUES (384, '520301', 'Ganancia en Venta de Activos Fijos', 383);
INSERT INTO public.cuenta_contable VALUES (385, '520302', 'Indemnizaciones por Siniestros', 383);
INSERT INTO public.cuenta_contable VALUES (386, '520303', 'Ingresos no reconocidos en ejercicios anteriores', 383);
INSERT INTO public.cuenta_contable VALUES (389, '6.101', 'PÉRDIDAS Y GANANCIAS', 388);
INSERT INTO public.cuenta_contable VALUES (390, '6.10101', 'Ejercicio Actual', 389);
INSERT INTO public.cuenta_contable VALUES (391, '6.10102', 'Ejercicios Anteriores', 389);
INSERT INTO public.cuenta_contable VALUES (394, '7.101', 'CUENTAS DE ORDEN', 393);
INSERT INTO public.cuenta_contable VALUES (395, '7.10101', 'Documentos Descontados', 394);
INSERT INTO public.cuenta_contable VALUES (396, '7.10102', 'Juicios Pendientes', 394);
INSERT INTO public.cuenta_contable VALUES (397, '7.10103', 'Mercaderías en consignación', 394);
INSERT INTO public.cuenta_contable VALUES (398, '7.10104', 'Valores Recibidos En Garantías', 394);
INSERT INTO public.cuenta_contable VALUES (399, '7.10105', 'Valores Dados En Garantía', 394);
INSERT INTO public.cuenta_contable VALUES (400, '7.10106', 'Disponibilidades Por Créditos Obtenidos', 394);
INSERT INTO public.cuenta_contable VALUES (402, '7.201', 'CONTRAPARTE DE CUENTAS DE ORDEN', 401);
INSERT INTO public.cuenta_contable VALUES (403, '7.20101', 'Documentos Descontados', 402);
INSERT INTO public.cuenta_contable VALUES (404, '7.20102', 'Juicios Pendientes', 402);
INSERT INTO public.cuenta_contable VALUES (405, '7.20103', 'Mercaderías en consignación', 402);
INSERT INTO public.cuenta_contable VALUES (406, '7.20104', 'Valores Recibidos En Garantías Por Contra', 402);
INSERT INTO public.cuenta_contable VALUES (407, '7.20105', 'Valores Dados En Garantía Por Contra', 402);
INSERT INTO public.cuenta_contable VALUES (408, '7.20106', 'Créditos Obtenidos Por Utilizar', 402);
INSERT INTO public.cuenta_contable VALUES (369, '5', 'CUENTAS DE RESULTADO ACREEDORAS', NULL);


--
-- Data for Name: partida_diaria_detalle; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.partida_diaria_detalle VALUES (1, 1, 5, 1, 25425.00);
INSERT INTO public.partida_diaria_detalle VALUES (2, 1, 193, 2, 2925.00);
INSERT INTO public.partida_diaria_detalle VALUES (3, 1, 372, 2, 22500.00);
INSERT INTO public.partida_diaria_detalle VALUES (8, 2, 5, 1, 6300.00);
INSERT INTO public.partida_diaria_detalle VALUES (9, 2, 193, 2, 1300.00);
INSERT INTO public.partida_diaria_detalle VALUES (10, 2, 372, 2, 10000.00);
INSERT INTO public.partida_diaria_detalle VALUES (11, 2, 8, 1, 5000.00);


--
-- Data for Name: tipo_saldo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_saldo VALUES (1, 'DEBE');
INSERT INTO public.tipo_saldo VALUES (2, 'HABER');


--
-- Data for Name: tipo_transaccion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_transaccion VALUES (1, 'compra al contado');
INSERT INTO public.tipo_transaccion VALUES (2, 'compra al credito');
INSERT INTO public.tipo_transaccion VALUES (3, 'venta al contado');
INSERT INTO public.tipo_transaccion VALUES (4, 'venta al credito');


--
-- Data for Name: transaccion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.transaccion VALUES (1, 1, '2025-08-17', 'se realiza una venta de $22500 al contado');
INSERT INTO public.transaccion VALUES (2, 1, '2025-08-18', 'se realiza una venta de $10000 al contado');


--
-- Name: cuenta_contable_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cuenta_contable_id_seq', 409, true);


--
-- Name: partida_diaria_detalle_id_partida_detalle_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.partida_diaria_detalle_id_partida_detalle_seq', 11, true);


--
-- Name: tipo_saldo_id_tipo_saldo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_saldo_id_tipo_saldo_seq', 2, true);


--
-- Name: tipo_transaccion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_transaccion_id_seq', 5, true);


--
-- Name: transaccion_id_transaccion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaccion_id_transaccion_seq', 2, true);


--
-- Name: cuenta_contable cuenta_contable_codigo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta_contable
    ADD CONSTRAINT cuenta_contable_codigo_key UNIQUE (codigo);


--
-- Name: cuenta_contable cuenta_contable_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta_contable
    ADD CONSTRAINT cuenta_contable_pkey PRIMARY KEY (id_cuenta_contable);


--
-- Name: partida_diaria_detalle partida_diaria_detalle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partida_diaria_detalle
    ADD CONSTRAINT partida_diaria_detalle_pkey PRIMARY KEY (id_partida_detalle);


--
-- Name: tipo_saldo tipo_saldo_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_saldo
    ADD CONSTRAINT tipo_saldo_nombre_key UNIQUE (nombre);


--
-- Name: tipo_saldo tipo_saldo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_saldo
    ADD CONSTRAINT tipo_saldo_pkey PRIMARY KEY (id_tipo_saldo);


--
-- Name: tipo_transaccion tipo_transaccion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_transaccion
    ADD CONSTRAINT tipo_transaccion_pkey PRIMARY KEY (id_tipo_transaccion);


--
-- Name: transaccion transaccion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT transaccion_pkey PRIMARY KEY (id_transaccion);


--
-- Name: cuenta_contable cuenta_contable_id_padre_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta_contable
    ADD CONSTRAINT cuenta_contable_id_padre_fkey FOREIGN KEY (id_padre) REFERENCES public.cuenta_contable(id_cuenta_contable);


--
-- Name: partida_diaria_detalle partida_diaria_detalle_id_cuenta_contable_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partida_diaria_detalle
    ADD CONSTRAINT partida_diaria_detalle_id_cuenta_contable_fkey FOREIGN KEY (id_cuenta_contable) REFERENCES public.cuenta_contable(id_cuenta_contable);


--
-- Name: partida_diaria_detalle partida_diaria_detalle_id_tipo_saldo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partida_diaria_detalle
    ADD CONSTRAINT partida_diaria_detalle_id_tipo_saldo_fkey FOREIGN KEY (id_tipo_saldo) REFERENCES public.tipo_saldo(id_tipo_saldo);


--
-- Name: partida_diaria_detalle partida_diaria_detalle_id_transaccion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partida_diaria_detalle
    ADD CONSTRAINT partida_diaria_detalle_id_transaccion_fkey FOREIGN KEY (id_transaccion) REFERENCES public.transaccion(id_transaccion);


--
-- Name: transaccion transaccion_id_tipo_transaccion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT transaccion_id_tipo_transaccion_fkey FOREIGN KEY (id_tipo_transaccion) REFERENCES public.tipo_transaccion(id_tipo_transaccion);


--
-- PostgreSQL database dump complete
--

