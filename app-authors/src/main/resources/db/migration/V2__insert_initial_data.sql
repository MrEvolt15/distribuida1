INSERT INTO public.authors VALUES (1, 2, 'Herbert Schildt');
INSERT INTO public.authors VALUES (1, 3, 'Kathy Sierra');
INSERT INTO public.authors VALUES (1, 4, 'Brian Goetz');
INSERT INTO public.authors VALUES (1, 5, 'Robert C. Martin');
INSERT INTO public.authors VALUES (3, 1, 'Joshua Bloch');

INSERT INTO public.books VALUES (45, 1, '978-0134686097', 'Effective Java');
INSERT INTO public.books VALUES (50, 1, '978-1260440232', 'Java: The Complete Reference');
INSERT INTO public.books VALUES (40, 1, '978-0596009205', 'Head First Java');
INSERT INTO public.books VALUES (55, 1, '978-0321349606', 'Java Concurrency in Practice');
INSERT INTO public.books VALUES (50, 1, '978-0136083238', 'Clean Code: A Handbook of Agile Software Craftsmanship');

INSERT INTO public.books_authors VALUES (1, '978-0134686097');
INSERT INTO public.books_authors VALUES (2, '978-1260440232');
INSERT INTO public.books_authors VALUES (3, '978-0596009205');
INSERT INTO public.books_authors VALUES (4, '978-0321349606');
INSERT INTO public.books_authors VALUES (5, '978-0136083238');
INSERT INTO public.books_authors VALUES (5, '978-0134686097');
INSERT INTO public.books_authors VALUES (1, '978-1260440232');
INSERT INTO public.books_authors VALUES (2, '978-0596009205');
INSERT INTO public.books_authors VALUES (3, '978-0321349606');
INSERT INTO public.books_authors VALUES (4, '978-0136083238');
INSERT INTO public.books_authors VALUES (1, '978-0136083238');
INSERT INTO public.books_authors VALUES (2, '978-0136083238');
