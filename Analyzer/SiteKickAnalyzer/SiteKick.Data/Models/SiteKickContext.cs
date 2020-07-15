using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace SiteKick.Data.Models
{
    public partial class SiteKickContext : DbContext
    {
        public SiteKickContext()
        {
        }

        public SiteKickContext(DbContextOptions<SiteKickContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Category> Category { get; set; }
        public virtual DbSet<Site> Site { get; set; }
        public virtual DbSet<SiteTech> SiteTech { get; set; }
        public virtual DbSet<Technology> Technology { get; set; }
        public virtual DbSet<TechnologyGroup> TechnologyGroup { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseSqlServer("Server=localhost;user=sa;password=123456;Database=SiteKick");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Category>(entity =>
            {
                entity.Property(e => e.Name).HasMaxLength(200);
            });

            modelBuilder.Entity<Site>(entity =>
            {
                entity.Property(e => e.Country).HasMaxLength(150);

                entity.HasOne(d => d.Category)
                    .WithMany(p => p.Site)
                    .HasForeignKey(d => d.CategoryId)
                    .HasConstraintName("FK_Site_Category");
            });

            modelBuilder.Entity<SiteTech>(entity =>
            {
                entity.HasKey(e => new { e.TechnologyId, e.SiteId });

                entity.HasOne(d => d.Site)
                    .WithMany(p => p.SiteTech)
                    .HasForeignKey(d => d.SiteId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_SiteTech_Site");

                entity.HasOne(d => d.Technology)
                    .WithMany(p => p.SiteTech)
                    .HasForeignKey(d => d.TechnologyId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_SiteTech_Technology");
            });

            modelBuilder.Entity<Technology>(entity =>
            {
                entity.Property(e => e.Description).HasMaxLength(500);

                entity.Property(e => e.Name).HasMaxLength(200);

                entity.HasOne(d => d.TechnologyGroup)
                    .WithMany(p => p.Technology)
                    .HasForeignKey(d => d.TechnologyGroupId)
                    .HasConstraintName("FK_Technology_TechnologyGroup");
            });

            modelBuilder.Entity<TechnologyGroup>(entity =>
            {
                entity.Property(e => e.Name).HasMaxLength(200);
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
