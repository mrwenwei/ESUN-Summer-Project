SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccountInfo](
	[account] [varchar](20) NOT NULL,
	[password] [varchar](20) NOT NULL,
	[role] [varchar](20) NULL,
	[name] [varchar](20) NULL,
	[branch_code] [varchar](20) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[AccountInfo] ADD PRIMARY KEY CLUSTERED 
(
	[account] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[AccountInfo]  WITH CHECK ADD FOREIGN KEY([branch_code])
REFERENCES [dbo].[EsunBranch] ([branch_code])
GO
