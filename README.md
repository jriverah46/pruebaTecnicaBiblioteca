<H1>Clonar proyecto</H1>
git clone https://github.com/jriverah46/pruebaTecnicaBiblioteca.git

<H1>configuracion base de datos</H1>
src/main/resources/application.properties
<li>
<ul>spring.datasource.url=jdbc:postgresql://localhost:5432/library</ul>
<ul>spring.datasource.username=postgres</ul>
<ul>spring.datasource.password=tu_password</ul>
<ul>spring.jpa.hibernate.ddl-auto=update</ul>
<ul>spring.jpa.show-sql=true</ul>
</li>
<h1>End points</h1>
<li>
  <ul>POST /api/auth/register — Registro de usuario (rol STUDENT por defecto)</ul>
  <ul>POST /api/auth/login — Login que retorna un JWT válido</ul>
  <ul>GET /api/books — Listar todos los libros con disponibilidad</ul>
 <ul> GET /api/books/{id} — Detalle de un libro</ul>
<ul>POST /api/books — Crear libro [solo ADMIN]</ul>
<ul>PUT /api/books/{id} — Actualizar libro [solo ADMIN]</ul>
 <ul>DELETE /api/books/{id} — Eliminar libro [solo ADMIN]</ul>
 <ul>POST /api/loans — Registrar nuevo préstamo (valida disponibilidad)</ul>
<ul>GET /api/loans/my — Ver mis préstamos activos [STUDENT/ADMIN]</ul>
<ul>GET /api/loans — Ver todos los préstamos [solo ADMIN]</ul>
<ul>PUT /api/loans/{id}/return — Registrar devolución [solo ADMIN]</ul>
   
</li>
