
import random

names = ["Aarav", "Vihaan", "Aditya", "Arjun", "Sai", "Ishaan", "Krishna", "Aryan", "Shaurya", "Atharva"]
surnames = ["Sharma", "Verma", "Gupta", "Malhotra", "Mehra", "Kapoor", "Singh", "Yadav", "Joshi", "Patel"]

sql_statements = []
for i in range(1, 101):
    first_name = random.choice(names)
    last_name = random.choice(surnames)
    full_name = f"{first_name} {last_name}"
    email = f"{first_name.lower()}.{last_name.lower()}{i}@example.com"
    password = f"password{i}"
    phone = f"98765{i:05d}"
    role = "USER"
    
    sql = f"INSERT INTO users (name, email, password, phone_number, role) VALUES ('{full_name}', '{email}', '{password}', '{phone}', '{role}');"
    sql_statements.append(sql)

with open("dummy_users.sql", "w") as f:
    f.write("\n".join(sql_statements))
print("SQL file generated successfully.")
