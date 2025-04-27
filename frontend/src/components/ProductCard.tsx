import { Produto } from "@/interfaces/Produto";

interface ProductCardProps {
    produto: Produto;
}


export default function ProductCard({ produto }: ProductCardProps) {
    return (
        <>
            <img
                alt={produto.descricao}
                src={produto.imagem}
                className="aspect-square w-full rounded-lg bg-gray-200 object-cover group-hover:opacity-75 xl:aspect-7/8" />
            <h3 className="mt-4 text-sm text-gray-700">{produto.nome}</h3>
            <p className="mt-1 text-lg font-medium text-gray-900">R$ {produto.preco.toFixed(2)}</p>
            <p className="mt-1 text-sm text-gray-500">{produto.disponilibidade}</p>
        </>

    )
}
