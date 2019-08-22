SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Banks](
	[SWIFTCODE] [varchar](5) NOT NULL,
	[name] [varchar](10) NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[Banks] ADD PRIMARY KEY CLUSTERED 
(
	[SWIFTCODE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
INSERT INTO [dbo].[Banks]
( -- Columns to insert data into
 [name], [SWIFTCODE]
)
VALUES
('台灣銀行','004'),
('土地銀行','005'),
('合作金庫','006'),
('第一銀行','007'),
('華南銀行','008'),
('彰化銀行','009'),
('上海銀行','011'),
('富邦銀行','012'),
('國泰世華','013'),
('高雄銀行','016'),
('兆豐商銀','017'),
('花旗銀行','021'),
('澳盛銀行','039'),
('王道銀行','048'),
('台灣企銀','050'),
('渣打銀行','052'),
('台中銀行','053'),
('滙豐銀行','081'),
('瑞興銀行','101'),
('華泰銀行','102'),
('新光銀行','103'),
('陽信銀行','108'),
('三信銀行','147'),
('中華郵政','700'),
('聯邦銀行','803'),
('遠東銀行','805'),
('元大銀行','806'),
('永豐銀行','807'),
('玉山銀行','808'),
('凱基銀行','809'),
('星展銀行','810'),
('台新銀行','812'),
('日盛銀行','815'),
('安泰銀行','816'),
('中國信託','822')
GO
