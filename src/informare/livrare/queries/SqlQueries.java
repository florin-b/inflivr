package informare.livrare.queries;

public class SqlQueries {

	public static String getCoordMasina() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select i.latitude, i.longitude from gps_masini g, borderouri b, gps_index i where ");
		sqlString.append(" replace(g.nr_masina,'-','')=  replace(b.masina,'-','') and ");
		sqlString.append(" b.numarb=? and b.sttrg in ('4','6') and g.id = i.device_id ");
		sqlString.append(" and not exists (select 1 from  sapprd.zevenimentsofer where document =? and client =?) ");

		return sqlString.toString();
	}

	public static String getCoordAdresa() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select ad.latitude, ad.longitude, c.nume from sapprd.zadreseclienti ad, ");
		sqlString.append(" sapprd.zdocumentebord z, clienti c  where z.nr_bord=? and c.cod = z.cod ");
		sqlString.append(" and z.cod = ad.codclient and z.adresa = ad.codadresa and z.cod=? ");

		return sqlString.toString();
	}

	public static String getArticoleComanda() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select ar.nume, p.kwmeng, p.vrkme ");
		sqlString.append(" from sapprd.vttp b, sapprd.vbfa f, sapprd.vbap p, sapprd.zdocumentebord d, articole ar ");
		sqlString.append(" where b.mandt = '900' ");
		sqlString.append(" and d.nr_bord = b.tknum ");
		sqlString.append(" and ar.cod = p.matnr ");
		sqlString.append(" and b.tknum = ? ");
		sqlString.append(" and d.cod= ? ");
		sqlString.append(" and b.tpnum = d.poz ");
		sqlString.append(" and b.mandt = f.mandt ");
		sqlString.append(" and b.vbeln = f.vbeln ");
		sqlString.append(" and f.vbtyp_v = 'C' ");
		sqlString.append(" and f.mandt = p.mandt ");
		sqlString.append(" and f.vbelv = p.vbeln ");
		sqlString.append(" and f.posnv = p.posnr ");

		return sqlString.toString();
	}

	public static String getVitezaMedieMF() {
		StringBuilder str = new StringBuilder();

		str.append(" select avg(vitezamedie) from sapprd.zvitezemedii ");
		str.append(" where datac >=? and tipmasina=? and filiala=? ");

		return str.toString();
	}

	public static String getDateMasinaBord() {

		StringBuilder str = new StringBuilder();

		str.append(" select avg(a.vitezamedie) vm, k.user1 from sapprd.zvitezemedii a, sapprd.aufk k, borderouri b ");
		str.append(" where k.mandt='900' and b.numarb =? and b.masina = k.ktext ");
		str.append(" and a.datac >=? and a.tipmasina=k.user1 and a.filiala=b.fili group by k.user1 ");

		return str.toString();
	}

	public static String getTipMasina() {

		StringBuilder str = new StringBuilder();
		str.append(
				" select k.user1, b.fili from sapprd.aufk k, borderouri b where mandt = '900' and b.numarb =? and b.masina = k.ktext ");

		return str.toString();

	}

	public static String getPozitieMasina() {
		StringBuilder str = new StringBuilder();

		str.append(" select latitude, longitude, mileage, speed from gps_index where device_id = ");
		str.append(" (select id from gps_masini where nr_masina = ");
		str.append(" (select replace(masina,'-','') nrauto from ( ");
		str.append(" select masina from websap.borderouri where numarb =?))) ");

		return str.toString();
	}

	public static String addCoordAdresa() {
		StringBuilder str = new StringBuilder();
		str.append(" insert into sapprd.zcoordadrese (mandt, idadresa, latitudine, longitudine) ");
		str.append(" values ('900',?,?,?)  ");
		return str.toString();

	}

	public static String addCoordAdresaClient() {
		StringBuilder str = new StringBuilder();

		str.append(" insert into sapprd.zadreseclienti (mandt, codclient, codadresa,  latitude, longitude) ");
		str.append(" values ('900',?,?,?,?)  ");

		return str.toString();
	}

	public static String verificaAdresa() {
		StringBuilder str = new StringBuilder();
		str.append(" select 1 from sapprd.zadreseclienti where mandt='900' and idadresa = ? ");

		return str.toString();
	}

	public static String verificaAdresaClient() {
		StringBuilder str = new StringBuilder();
		str.append(" select 1 from sapprd.zadreseclienti where mandt='900' and codclient = ? and codadresa = ? ");

		return str.toString();
	}

	public static String getClientiBorderouFromDB() {

		StringBuilder str = new StringBuilder();
		str.append(" select a.codclient, a.codadresa, a.distclant, a.initkm from ");
		str.append(" sapprd.ztraseuborderou a where a.borderou=? ");
		str.append(" order by to_number(a.poz) ");
		return str.toString();
	}

	public static String getClientiBorderou() {

		StringBuilder str = new StringBuilder();

		str.append(" select  a.cod_client, c.nume, a.adresa_client, b.city1,b.street,b.house_num1,b.region, ");
		str.append(" nvl(d.latitude,'0') latitudine, nvl(d.longitude, '0') longitudine , a.adresa_client from ");
		str.append(" sapprd.zdocumentesms a, sapprd.adrc b, clienti c, sapprd.zadreseclienti d,  ");
		str.append(" sapprd.zordinelivrari o where ");
		str.append(" a.nr_bord =?  and d.codadresa(+) = a.adresa_client and d.codclient(+) = a.cod_client ");
		str.append(" and b.client = '900' and c.cod = a.cod_client ");
		str.append(" and b.addrnumber = a.adresa_client ");
		str.append(" and o.borderou(+) = a.nr_bord and o.client(+) = a.cod_client   ");
		str.append(" and o.codadresa(+) = a.adresa_client  order by o.pozitie ");

		return str.toString();
	}

	public static String saveEtapeBorderou() {
		StringBuilder str = new StringBuilder();
		str.append(
				" insert into sapprd.ztraseuborderou (mandt, borderou, poz, codclient, codadresa, distclant, initkm ) ");
		str.append(" values ('900',?,?,?,?,?,?)");
		return str.toString();
	}

	public static String getPozitieLivrare() {
		StringBuilder str = new StringBuilder();

		str.append(" select pozitie from sapprd.zordinelivrari where mandt='900' and borderou=? and client=? ");

		return str.toString();

	}

}
