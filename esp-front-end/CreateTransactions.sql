DROP TABLE Transactions
DROP FUNCTION udf_ComputeColumn
CREATE FUNCTION udf_ComputeColumn
(
    @id int,
    @CreateDate date
)
RETURNS char(12) 
AS
BEGIN
DECLARE @revtal char(12),
        @NumberOfRowsToday int

-- get the number of rows inserted in the same day before the current row
SELECT @NumberOfRowsToday = COUNT(*) 
FROM dbo.Transactions 
WHERE caldate = @CreateDate
AND id < @id

-- calculate the string to return
SELECT @revtal = CONVERT(char(6), @CreateDate, 12) + CAST(@NumberOfRowsToday as varchar)
RETURN @revtal

END

CREATE TABLE Transactions
(
    -- id uniqueidentifier NOT NULL DEFAULT newid() PRIMARY key,
    id int identity(1,1) PRIMARY key,
    caldate date default(GETDATE()),
    datetime DATETIME default(GETDATE()),
    tnum as dbo.udf_ComputeColumn(id,caldate), -- here is computed id
    type VARCHAR(20) not null, 
    branch_code varchar(20),
    broker VARCHAR(20),   
    receipts_data varchar(MAX),
    cash_detail VARCHAR(MAX),
    back_data VARCHAR(MAX),
    data_version VARCHAR(20),
    finished_condition int DEFAULT 0,
    reviewed_condition int DEFAULT 0,
    finished TINYINT DEFAULT(0),
    reviewed TINYINT DEFAULT(0),
    finished_time DATETIME,
)

insert into Transactions(type) VALUES('123')
insert into Transactions(type) VALUES('123')
SELECT * FROM Transactions 