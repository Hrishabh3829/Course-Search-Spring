<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--   <title>Course Search Backend - Documentation</title> -->
</head>
<body>
  <h1>🎯 Course Search Backend (Spring Boot + Elasticsearch)</h1>
  <p>This is a lightweight backend application for searching and filtering courses using <strong>Elasticsearch</strong>. It supports full-text search, autocomplete, and optional fuzzy matching — built with <strong>Spring Boot</strong>, <strong>Java 17</strong>, and tested using <strong>Testcontainers</strong>.</p>

  <div class="section">
    <h2>🚀 Getting Started</h2>
    <h3>1. Prerequisites</h3>
    <ul>
      <li>✅ Java 17</li>
      <li>✅ Maven 3+</li>
      <li>✅ Docker (for Elasticsearch container)</li>
    </ul>

    <h3>2. Clone the Repository</h3>
    <pre><code>git clone https://github.com/your-username/course-search.git
cd course-search</code></pre>

    <h3>3. Start Elasticsearch with Docker Compose</h3>
    <pre><code>docker-compose up -d</code></pre>

    <h3>4. Run the Spring Boot Application</h3>
    <pre><code>./mvnw spring-boot:run</code></pre>

    <h3>5. Verify Initialization</h3>
    <p>You should see:</p>
    <pre><code>Courses indexed into Elasticsearch: 50</code></pre>
  </div>

  <div class="section">
    <h2>📌 Features</h2>
    <ul>
      <li>🔍 Full-text search (title, description, category)</li>
      <li>⚡ Autocomplete suggestions (prefix-based)</li>
      <li>🤖 Fuzzy matching for typo-tolerance</li>
      <li>📝 50 sample courses indexed at startup</li>
      <li>🧪 Integration tests using Testcontainers + JUnit 5</li>
    </ul>
  </div>

  <div class="section">
    <h2>🧱 Tech Stack</h2>
    <table class="table">
      <thead>
        <tr><th>Tech</th><th>Version</th></tr>
      </thead>
      <tbody>
        <tr><td>Java</td><td>17</td></tr>
        <tr><td>Spring Boot</td><td>3.2+</td></tr>
        <tr><td>Elasticsearch</td><td>7.17</td></tr>
        <tr><td>Spring Data ES</td><td>✅</td></tr>
        <tr><td>Jackson</td><td>✅</td></tr>
        <tr><td>Testcontainers</td><td>1.19.0</td></tr>
        <tr><td>JUnit 5</td><td>5.12.2</td></tr>
      </tbody>
    </table>
  </div>

  <div class="section">
    <h2>🗂️ Project Structure</h2>
    <pre><code>src/
├── main/
│   ├── java/com/example/coursesearch/
│   │   ├── config/         # Jackson & other app-level configs
│   │   ├── controller/     # API endpoints
│   │   ├── document/       # Elasticsearch @Document definitions
│   │   ├── repository/     # Spring Data Elasticsearch repositories
│   │   └── service/        # Business logic (search, indexing)
│   └── resources/
│       ├── application.yml
│       └── sample-courses.json  # Initial 50-course dataset
└── test/                   # Integration testing with Testcontainers</code></pre>
  </div>

  <div class="section">
    <h2>🔎 API Endpoints</h2>
    <table class="table">
      <thead>
        <tr><th>Endpoint</th><th>Method</th><th>Description</th></tr>
      </thead>
      <tbody>
        <tr>
          <td><code>/api/search?q=keyword</code></td>
          <td>GET</td>
          <td>Full-text search across title, description, category</td>
        </tr>
        <tr>
          <td><code>/api/search/suggest?q=term</code></td>
          <td>GET</td>
          <td>Autocomplete suggestions using Elasticsearch completion field</td>
        </tr>
      </tbody>
    </table>
  </div>

  <div class="section">
    <h2>🧪 Running Tests</h2>
    <pre><code>./mvnw test</code></pre>
    <p>This project uses <strong>Testcontainers</strong> to spin up a real Elasticsearch instance. Useful to ensure your logic works on actual Elasticsearch behavior.</p>
  </div>

  <div class="section">
    <h2>🙌 How to Use This Project</h2>
    <ol>
      <li>Clone the repo</li>
      <li>Start Elasticsearch using <code>docker-compose up -d</code></li>
      <li>Run the Spring Boot backend using <code>./mvnw spring-boot:run</code></li>
      <li>Open Postman or your browser and hit:</li>
        <ul>
          <li><code>http://localhost:9090/api/search?q=math</code></li>
          <li><code>http://localhost:9090/api/search/suggest?q=art</code></li>
        </ul>
    </ol>
  </div>

  <div class="section">
    <h2>👍 Contributing</h2>
    <p>This project is beginner-friendly. You are welcome to fork the repo and open PRs for improvements or suggestions.</p>
  </div>

  <div class="section">
    <h2>🙏 Acknowledgement</h2>
    <p>Built as a learning project to explore Spring Boot and Elasticsearch integration. Thanks to the developer community for docs, samples, and support.</p>
  </div>

  <div class="section">
    <h2>👤 Author</h2>
    <p>Maintained by <strong>Hrishabh</strong>.<br>
    Built with passion, patience, and a focus on clean backend design.</p>
  </div>

</body>
</html>
