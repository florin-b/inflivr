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

}
