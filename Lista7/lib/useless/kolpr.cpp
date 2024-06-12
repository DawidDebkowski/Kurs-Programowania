#include <iostream>
#include <queue>


int main(int argc, char ** argv)
{
	// Kolejka priorytetowa (elementy od najwiekszego do najmniejszego)
	std::priority_queue<int> queue;

	queue.push(100);
	queue.push(300);
	queue.push(50);
	queue.push(150);

	std::cout << "Prechodzenie po elementach" << std::endl;
	while (!queue.empty())
	{
		// Wypisywanie najwiekszego elementu
		std::cout << queue.top() << std::endl;
		// Usuwanie najwiekszego elementu
		queue.pop();
	}

	return 0;
}