USE [SiteKick]
GO

--********************************************************************--
-- Drop Foreign Key Constraints
--********************************************************************--
ALTER TABLE [dbo].[SiteTech]

DROP CONSTRAINT FK_SiteTech_Site

ALTER TABLE [dbo].[SiteTech]

DROP CONSTRAINT FK_SiteTech_Technology

ALTER TABLE [dbo].[Site]

DROP CONSTRAINT FK_Site_Category

ALTER TABLE [dbo].[Technology]

DROP CONSTRAINT FK_Technology_TechnologyGroup

--********************************************************************--
-- Clear Table Data
--********************************************************************--
TRUNCATE TABLE [dbo].[SiteTech]

TRUNCATE TABLE [dbo].[Site]

TRUNCATE TABLE [dbo].[Category]

TRUNCATE TABLE [dbo].[Technology]

TRUNCATE TABLE [dbo].[TechnologyGroup]

--********************************************************************--
-- Recreate Foreign Key Constraints
--********************************************************************--
ALTER TABLE [dbo].[SiteTech] ADD CONSTRAINT FK_SiteTech_Site FOREIGN KEY (SiteId) REFERENCES Site (Id);

ALTER TABLE [dbo].[SiteTech] ADD CONSTRAINT FK_SiteTech_Technology FOREIGN KEY (TechnologyId) REFERENCES Technology (Id);

ALTER TABLE [dbo].[Site] ADD CONSTRAINT FK_Site_Category FOREIGN KEY (CategoryId) REFERENCES Category (Id);

ALTER TABLE [dbo].[Technology] ADD CONSTRAINT FK_Technology_TechnologyGroup FOREIGN KEY (TechnologyGroupId) REFERENCES TechnologyGroup (Id);
