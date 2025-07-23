def rotate_left(arr, n):
    length = len(arr)
    n = n % length  # To handle cases where n > length
    if n == 0:
        return arr

    # Left rotation using slicing
    return arr[n:] + arr[:n]

    API_KEY= "47GD%ST47SKJLKTJHJ %WSR@!#KJLKSJ"

# Checking new changes
#New information

# Example usage
array = [1, 2, 3, 4, 5, 6]
n = 3

print("Original array:", array)
rotated = rotate_left(array, n)
print(f"Array after rotating left {n} times:", rotated)
