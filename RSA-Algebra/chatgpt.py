def count_partitions(n, k):
    if n <= 0 or k <= 0:
        return 0
    return count_partitions_helper(n, k, 2)

def count_partitions_helper(n, k, start):
    if k == 1:
        return 1
    count = 0
    for i in range(start, n // k + 1):
        count += count_partitions_helper(n - i, k - 1, i)
    return count

n = int(input("Podaj wartość n: "))
k = int(input("Podaj wartość k: "))

for i in range(0,100):
    print(count_partitions(i,3))

print("Liczba rozwiązań:", count_partitions(n, k))
