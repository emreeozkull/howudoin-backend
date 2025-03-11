# HowUDoin

A modern social messaging platform built with Spring Boot that enables users to connect with friends, send messages, and participate in group conversations. The platform features secure JWT-based authentication and real-time messaging capabilities.

## 🚀 Features

- User authentication with JWT tokens
- Friend management system (add, accept, and view friends)
- Private messaging between friends
- Group chat functionality
- RESTful API endpoints
- Secure password handling
- MongoDB integration for data persistence
- Containerized with Docker for easy deployment

## 🛠️ Technologies

- Java
- Spring Boot
- MongoDB
- Spring Security
- JWT Authentication
- Gradle
- Docker
- Lombok

## 📋 Prerequisites

- Java 17 or higher
- Gradle 7.x or higher
- Docker (optional, for containerization)
- MongoDB

## 🔧 Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/howudoin.git
cd howudoin
```

2. Configure MongoDB:
   - Update `application.properties` with your MongoDB connection details
   - Ensure MongoDB is running on your system

3. Build the project:
```bash
./gradlew build
```

4. Run the application:
```bash
./gradlew bootRun
```

### 🐳 Docker Installation

1. Build the Docker image:
```bash
docker build -t howudoin .
```

2. Run the container:
```bash
docker run -p 8080:8080 howudoin
```

## 🔌 API Endpoints

### Authentication
- `POST /register` - Register a new user
- `POST /login` - Login and receive JWT token

### User Management
- `GET /get-all-users` - Get list of all users
- `POST /friends/add` - Send friend request
- `POST /friends` - Get list of friends
- `POST /friends/accept` - Accept friend request
- `POST /friends/getFriendRequests` - Get pending friend requests

### Messaging
- `POST /messages/send` - Send a message to a friend
- `POST /messages` - Get conversation history

### Groups
- `GET /groups` - Get all groups for current user
- `POST /groups/create` - Create a new group
- `POST /groups/{groupId}/send` - Send message to group
- `POST /groups/{groupId}/add-member` - Add member to group
- `GET /groups/{groupId}/members` - Get group members
- `GET /groups/{groupId}/messages` - Get group messages
- `GET /groups/{groupId}/getGroupDetails` - Get group details

## 🛠️ Development

### Building

```bash
./gradlew build
```

### Testing

```bash
./gradlew test
```

## 📝 Configuration

Key configuration settings in `application.properties`:
```properties
spring.data.mongodb.database=howudoin-mongodb
spring.data.mongodb.host=your-mongodb-host
spring.data.mongodb.port=27017
jwt.secret=your-jwt-secret-key
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- Your Name - [Your GitHub Profile]

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- MongoDB team for the robust database
- The open-source community for their invaluable contributions
