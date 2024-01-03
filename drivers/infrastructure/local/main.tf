terraform {
  required_providers {
    docker = {
      source = "kreuzwerker/docker"
      version = "3.0.2"
    }
  }
}

provider "docker" {
    host = "unix:///var/run/docker.sock"
}

resource "docker_container" "drivers_db" {
  image = "postgres:14"
  name  = "drivers_db"
    ports {
        internal = 5432
        external = 5432
    }
  env = [
    "POSTGRES_USER=drivers",
    "POSTGRES_PASSWORD=drivers",
    "POSTGRES_DB=drivers",
  ]
}