import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

# Email configurations
sender_email = 'jonas.sandelmann@ka-raceing.de'
receiver_email = 'thore.haupt@ka-raceing.de'
subject = 'test1'
body = 'aus python'

# Google SMTP server and port
smtp_server = 'smtp.gmail.com'
port = 465  # For SSL

# Email account credentials
username = 'jonas.sandelmann@ka-raceing.de'
password = "Dein Passwort"  # Or your Google account password if less secure apps access is enabled

# Create a multipart message and set headers
message = MIMEMultipart()
message['From'] = sender_email
message['To'] = receiver_email
message['Subject'] = subject

# Add body to email
message.attach(MIMEText(body, 'plain'))

# Create SMTP session for sending the mail
try:
    server = smtplib.SMTP_SSL(smtp_server, port)
    server.login(username, password)
    text = message.as_string()
    server.sendmail(sender_email, receiver_email, text)s
    print('Email sent successfully!')
except Exception as e:
    print('Something went wrong:', e)
finally:
    server.quit()