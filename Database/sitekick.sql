USE [master]
GO
/****** Object:  Database [SiteKick]    Script Date: 20/07/2020 10:57:01 PM ******/
CREATE DATABASE [SiteKick]
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SiteKick].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SiteKick] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SiteKick] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SiteKick] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SiteKick] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SiteKick] SET ARITHABORT OFF 
GO
ALTER DATABASE [SiteKick] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SiteKick] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SiteKick] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SiteKick] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SiteKick] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SiteKick] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SiteKick] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SiteKick] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SiteKick] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SiteKick] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SiteKick] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SiteKick] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SiteKick] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SiteKick] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SiteKick] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SiteKick] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SiteKick] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SiteKick] SET RECOVERY FULL 
GO
ALTER DATABASE [SiteKick] SET  MULTI_USER 
GO
ALTER DATABASE [SiteKick] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SiteKick] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SiteKick] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SiteKick] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SiteKick] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'SiteKick', N'ON'
GO
ALTER DATABASE [SiteKick] SET QUERY_STORE = OFF
GO
USE [SiteKick]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 20/07/2020 10:57:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NULL,
	[Password] [nvarchar](100) NULL,
	[Role] [nvarchar](50) NULL,
	[IsActive] [bit] NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 20/07/2020 10:57:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](200) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Site]    Script Date: 20/07/2020 10:57:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Site](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Url] [nvarchar](max) NULL,
	[GlobalRank] [int] NULL,
	[Country] [nvarchar](150) NULL,
	[CountryRank] [int] NULL,
	[CategoryId] [int] NULL,
 CONSTRAINT [PK_Site] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SiteTech]    Script Date: 20/07/2020 10:57:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SiteTech](
	[TechnologyId] [int] NOT NULL,
	[SiteId] [int] NOT NULL,
 CONSTRAINT [PK_SiteTech] PRIMARY KEY CLUSTERED 
(
	[TechnologyId] ASC,
	[SiteId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Technology]    Script Date: 20/07/2020 10:57:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Technology](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](200) NULL,
	[Description] [nvarchar](500) NULL,
	[TechnologyGroupId] [int] NULL,
 CONSTRAINT [PK_Technology] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TechnologyGroup]    Script Date: 20/07/2020 10:57:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TechnologyGroup](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](200) NULL,
 CONSTRAINT [PK_TechnologyGroup] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_Username]    Script Date: 20/07/2020 10:57:01 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UQ_Username] ON [dbo].[Account]
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Site]  WITH CHECK ADD  CONSTRAINT [FK_Site_Category] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Category] ([Id])
GO
ALTER TABLE [dbo].[Site] CHECK CONSTRAINT [FK_Site_Category]
GO
ALTER TABLE [dbo].[SiteTech]  WITH CHECK ADD  CONSTRAINT [FK_SiteTech_Site] FOREIGN KEY([SiteId])
REFERENCES [dbo].[Site] ([Id])
GO
ALTER TABLE [dbo].[SiteTech] CHECK CONSTRAINT [FK_SiteTech_Site]
GO
ALTER TABLE [dbo].[SiteTech]  WITH CHECK ADD  CONSTRAINT [FK_SiteTech_Technology] FOREIGN KEY([TechnologyId])
REFERENCES [dbo].[Technology] ([Id])
GO
ALTER TABLE [dbo].[SiteTech] CHECK CONSTRAINT [FK_SiteTech_Technology]
GO
ALTER TABLE [dbo].[Technology]  WITH CHECK ADD  CONSTRAINT [FK_Technology_TechnologyGroup] FOREIGN KEY([TechnologyGroupId])
REFERENCES [dbo].[TechnologyGroup] ([Id])
GO
ALTER TABLE [dbo].[Technology] CHECK CONSTRAINT [FK_Technology_TechnologyGroup]
GO
USE [master]
GO
ALTER DATABASE [SiteKick] SET  READ_WRITE 
GO
