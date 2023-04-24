# Encoder File
# Caleb Millard
# Lab 7 CMPT 360
# Title: malware platform deployment
# Dr Rick Sutcliffe
# Program 1/2
from PIL import Image


def encode_message(image_path, message_path, output_path):
    # Open the image
    with open(message_path, "r") as file:
        message = file.read()
    image = Image.open(image_path)

    # Convert the message to binary
    binary_message = "".join(format(ord(char), "08b") for char in message)
    # Get the size of the image in pixels
    width, height = image.size

    # Check if the image can fit the entire message
    required_pixels = len(binary_message)
    total_pixels = width * height * 3  # 3 channels (RGB)
    if required_pixels > total_pixels:
        print("Error: Message is too long to be encoded into the image.")
        return

    # Iterate through each pixel in the image
    encoded_pixels = 0
    for y in range(height):
        for x in range(width):
            pixel = list(image.getpixel((x, y)))

            # Encode the binary message into the pixel values
            if encoded_pixels < required_pixels:
                # Loop through R, G, B traits
                for i in range(3):
                    if encoded_pixels < required_pixels:
                        pixel[i] = (pixel[i] & 254) | int(
                            binary_message[encoded_pixels]
                        )
                        encoded_pixels += 1
            print(pixel)
            # Update the pixel in the image
            image.putpixel((x, y), tuple(pixel))

    # Save the encoded image
    image.save(output_path)
    print(f"Message encoded and saved to '{output_path}'.")


# these have been negated and not given because if i am going to post anywhere it needs to follow laws
# any and all file paths have been treated as such and negated to disable to program
message_path = "desiredpayload.py"
input_file = "desiredImage.jpeg"
output_file = "header.jpeg"
encode_message(input_file, message_path, output_file)
