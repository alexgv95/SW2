
<ul> 
{
for $x in doc("xml/bibliotecas.xml")/ContenedorBibliotecas/listaBibliotecas
order by $x/@facultad
return <li>{$x}</li>
}
</ul>
