-- 会社テーブル
INSERT INTO COMPANYTABLE (
    COMPANY
    )VALUES (
    '株式会社ABC'
    );
    
    INSERT INTO COMPANYTABLE (
    COMPANY
    )VALUES (
    '株式会社あいうえお'
    );
    
   -- DELETE FROM COMPANYTABLE;


INSERT INTO PERSONALINFOTABLE (
	NAME,
	COMPANYID,
	DEPARTMENT,
	POST,
	ADDRESS,
	TEL,
	MAIL,
	PROJECTTITLE,
	REMARKS
    )
	VALUES (
		'斎藤　太郎',
		1,
		'営業部',
		'部長',
		'東京都新宿区123-456',
		'080-1111-9999',
		'saito.taro@gmail.com',
		'商品開発について',
		'特徴：メガネをかけている'
	);
	
INSERT INTO PERSONALINFOTABLE (
	NAME,
	COMPANYID,
	DEPARTMENT,
	POST,
	ADDRESS,
	TEL,
	MAIL,
	PROJECTTITLE,
	REMARKS
	) 
	VALUES (
		'田中　次郎',
		2,
		'経理部',
		'課長',
		'大阪府大阪888',
		'070-5555-7777',
		'tanaka.jiro@gmail.com',
		'経費削減の相談',
		''
	);
    
     --   DELETE FROM PERSONALINFOTABLE;

