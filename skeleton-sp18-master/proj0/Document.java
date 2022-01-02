Document.java
		for (Planet planet : planets) {
			planet.draw();
		}

		for (double time = 0; time < T; time += dt) {
			double[] xForces = new double[n];
			double[] yForces = new double[n];
			
			for (int i = 0; i < n; i++) {
				xForces[i] = planets[i].calcForceExertedByX(planets);
				yForces[i] = planets[i].calcForceExertedByY(planets);
			}
			for (int i = 0; i < n; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/" + imageToDraw);
			for (Planet planet : planets) {
				planet.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
		}