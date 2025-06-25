using commentaire_service.Startup;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();

builder.Services.UseDbContextConfiguration(builder.Configuration.GetConnectionString("CommentaireContext"));

var app = builder.Build();

app.UseHttpsRedirection();
app.MapControllers();

app.Run();
