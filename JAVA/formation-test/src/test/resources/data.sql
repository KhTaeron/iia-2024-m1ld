INSERT INTO fournisseur (fou_id, fou_name)
VALUES
    ('f1', 'F1'),
    ('f2', 'F2'),
    ('f3', 'F3'),
    ('f4', 'F4');

INSERT INTO produit (pro_id, pro_name, pro_price, pro_date, pro_fournisseur_id)
VALUES ('produitId', 'Produit Nom', 8526, '2024-10-02 11:00:53', 'f2');
