def sum(data):
    
    total = 0
    for value in data:
        total += value
    return total

def mean(data):
    
    if len(data) == 0:
        return 0  
    total = 0
    for value in data:
        total += value
    return total / len(data)

def minimum(data):
    
    if len(data) == 0:
        return None  
    min_val = data[0]
    for value in data:
        if value < min_val:
            min_val = value
    return min_val

def maximum(data):
    
    if len(data) == 0:
        return None  
    max_val = data[0]
    for value in data:
        if value > max_val:
            max_val = value
    return max_val
