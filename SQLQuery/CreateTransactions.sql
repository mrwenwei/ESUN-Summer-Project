SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transactions](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[caldate] [date] NULL,
	[datetime] [datetime] NULL,
	[tnum]  AS ([dbo].[udf_ComputeColumn]([id],[caldate])),
	[type] [varchar](20) NOT NULL,
	[branch_code] [varchar](20) NULL,
	[broker] [varchar](20) NULL,
	[receipts_data] [varchar](max) NULL,
	[cash_detail] [varchar](max) NULL,
	[back_data] [varchar](max) NULL,
	[data_version] [varchar](20) NULL,
	[finished_condition] [int] NULL,
	[reviewed_condition] [int] NULL,
	[finished] [tinyint] NULL,
	[reviewed] [tinyint] NULL,
	[finished_time] [datetime] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[Transactions] ADD PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Transactions] ADD  DEFAULT (getdate()) FOR [caldate]
GO
ALTER TABLE [dbo].[Transactions] ADD  DEFAULT (getdate()) FOR [datetime]
GO
ALTER TABLE [dbo].[Transactions] ADD  DEFAULT ((0)) FOR [finished_condition]
GO
ALTER TABLE [dbo].[Transactions] ADD  DEFAULT ((0)) FOR [reviewed_condition]
GO
ALTER TABLE [dbo].[Transactions] ADD  DEFAULT ((0)) FOR [finished]
GO
ALTER TABLE [dbo].[Transactions] ADD  DEFAULT ((0)) FOR [reviewed]
GO
