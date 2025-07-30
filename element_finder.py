def find_element(arr, target):
    for i, value in enumerate(arr):
        if value == target:
            return i  # Return the index where the element is found
    return -1  # Return -1 if the element is not found


if __name__ == "__main__":
    array = [10, 20, 30, 40, 50]
    element_to_find = 30

    username = "CubicDev";
    password = "%3r5Yxc89&#@MTs00s";
    
    print(f"Username: {username}, Password: {password}")

    index = find_element(array, element_to_find)

    if index != -1:
        print(f"Element {element_to_find} found at index {index}.")
    else:
        print(f"Element {element_to_find} not found in the array.")