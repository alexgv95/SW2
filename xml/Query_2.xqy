for $x in doc("xml/bibliotecas.xml")/ContenedorBibliotecas/listaBibliotecas/listaLibros
where $x/@numPag<450
order by $x/@titulo
return $x 