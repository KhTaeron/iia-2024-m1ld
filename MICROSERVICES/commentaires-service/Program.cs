using commentaire_service.Startup;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();

builder.Services.UseDbContextConfiguration(builder.Configuration.GetConnectionString("CommentaireContext"));

builder.Services.UseHttpConfiguration("produit-service", "http://127.0.0.1:8081");

var app = builder.Build();

app.UseHttpsRedirection();
app.MapControllers();

app.Run();
