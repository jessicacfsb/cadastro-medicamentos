CREATE ALIAS IF NOT EXISTS P_PATIENT_AGE AS $$
int pPatientAge(java.sql.Connection conn, long patientId) throws Exception {

    String sql =
        "SELECT DATEDIFF('YEAR', DATE_OF_BIRTH, CURRENT_DATE) " +
        "- CASE " +
        "    WHEN DATEADD('YEAR', " +
        "        DATEDIFF('YEAR', DATE_OF_BIRTH, CURRENT_DATE), " +
        "        DATE_OF_BIRTH) > CURRENT_DATE " +
        "    THEN 1 " +
        "    ELSE 0 " +
        "  END " +
        "FROM PATIENT " +
        "WHERE ID = ?";

    try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setLong(1, patientId);

        try (java.sql.ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    }

    return 0;
}
$$;