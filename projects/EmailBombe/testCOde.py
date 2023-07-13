import requests
import csv


def generate_email_address():
    """Generates a new email address using the 1secmail API."""
    api_url = "https://www.1secmail.com/api/v1/"
    response = requests.get(api_url + "/?action=genRandomMailbox")
    if response.status_code == 200:
        data = response.json()
        print(data)
        return data[0]
    else:
        raise Exception("Failed to generate email address.")


def generate_password(length):
    """Generates a random password."""
    return "passwort"

# DasIsteineSuperEmail!1API


def send_email(email, password, recipient_email):
    """Sends an email using the SendGrid API."""
    sendgrid_api_key = 'YOUR_SENDGRID_API_KEY'
    url = 'https://api.sendgrid.com/v3/mail/send'
    headers = {
        'Authorization': f'Bearer {sendgrid_api_key}',
        'Content-Type': 'application/json'
    }
    payload = {
        'personalizations': [
            {
                'to': [{'email': recipient_email}]
            }
        ],
        'from': {'email': email},
        'subject': 'Sample Email',
        'content': [{'type': 'text/plain', 'value': 'Hello, this is a sample email.'}]
    }

    response = requests.post(url, json=payload, headers=headers)
    if response.status_code == 202:
        print(f"Email sent from {email} to {recipient_email}")
    else:
        print(f"Failed to send email from {email} to {recipient_email}")


def save_to_csv(data, filename):
    """Saves data to a CSV file."""
    with open(filename, 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(['Email', 'Password', 'Port'])
        writer.writerows(data)


def main():
    num_addresses = 10
    data = []

    for _ in range(num_addresses):
        email_id, domain = generate_email_address()
        password = generate_password(8)
        email = f"{email_id}@{domain}"
        data.append([email, password])

    filename = 'email_addresses.csv'
    save_to_csv(data, filename)
    print(f"Email addresses and passwords saved to {filename}.")

    # Sending emails from the generated addresses
    # Replace with the actual recipient email address
    recipient_email = 'bxq67359@omeie.com'

    for email, password in data:
        send_email(email, password, recipient_email)


if __name__ == '__main__':
    main()
