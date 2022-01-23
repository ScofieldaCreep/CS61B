import java.util.Arrays;

public class UnionFind {
	int[] union;

	public UnionFind(int n) {
		union = new int[n];
		Arrays.fill(union, -1);
	}

	public void validate(int v1) {
		if (v1 >= union.length) {
			throw new IllegalArgumentException();
		}
	}

	public int sizeOf(int v1) {
		return Math.abs(union[find(v1)])
	}

	public int parent(int v1) {
		return union[v1];
	}

	public boolean connected(int v1, int v2) {
		return find(v1) == find(v2);
	}

	public void union(int v1, int v2) {
		validate(v1);
		validate(v2);
		if (find(v1) != find(v2)) {
			if (sizeOf(v1) <= sizeOf(v2)) {
				union[find(v2)] += union[find(v1)];
				union[find(v1)] = find(v2);
			} else {
				union[find(v1)] += union[find(v2)];
				union[find(v2)] = find(v1);
			}
		}
	}

	public int find(int v1) {
		validate(v1);
		int p = v1;
		while (union[p] >= 0) {
			p = parent(p);
		}
		return p;
	}

}