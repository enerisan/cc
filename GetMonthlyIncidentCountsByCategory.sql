DELIMITER //

DROP PROCEDURE IF EXISTS GetMonthlyIncidentCountsByCategory//

CREATE PROCEDURE GetMonthlyIncidentCountsByCategory(
    IN p_year INT,
    IN p_city_id INT
)
BEGIN
    -- Tabla temporal de colores por categoría
    CREATE TEMPORARY TABLE IF NOT EXISTS tmp_category_colors (
        category_name VARCHAR(255),
        color VARCHAR(7)
    );

    INSERT INTO tmp_category_colors (category_name, color) VALUES
        ('Voirie', '#1f77b4'),
        ('Éclairage public', '#ff7f0e'),
        ('Trottoirs', '#2ca02c'),
        ('Conteneurs à déchets', '#d62728'),
        ('Aire de jeux', '#9467bd'),
        ('Fontaines', '#8c564b'),
        ('Propreté', '#e377c2'),
        ('Gestion des déchets', '#7f7f7f'),
        ('Espaces verts', '#bcbd22'),
        ('Installations sportives', '#17becf'),
        ('Bâtiments publics', '#aec7e8'),
        ('Nuisibles', '#ffbb78'),
        ('Égouts', '#98df8a'),
        ('Circulation', '#ff9896'),
        ('Autres', '#c5b0d5'),
        ('Accessibilité', '#17a589');

    -- Consulta principal
    SELECT 
        LEFT(MONTHNAME(DATE(CONCAT(p_year, '-', LPAD(m.month,2,'0'), '-01'))), 3) AS month_name,
        cat.name AS category_name,
        GREATEST(COUNT(i.id), 0) AS incident_count,
        tmp.color AS category_color
    FROM 
        (SELECT 1 AS month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION 
         SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION 
         SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12) AS m
    CROSS JOIN ccdb.category AS cat
    LEFT JOIN ccdb.incident_category AS icat ON icat.category_id = cat.id
    LEFT JOIN ccdb.incident AS i 
        ON i.id = icat.incident_id 
        AND YEAR(i.created_at) = p_year
        AND MONTH(i.created_at) = m.month
        AND i.city_id = p_city_id
    LEFT JOIN tmp_category_colors tmp ON tmp.category_name = cat.name
    GROUP BY m.month, cat.id, cat.name, tmp.color
    ORDER BY m.month, cat.name;

    DROP TEMPORARY TABLE IF EXISTS tmp_category_colors;
END//

DELIMITER ;
