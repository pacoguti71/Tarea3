        binding.textViewCerrarSesion.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            requireActivity().finish();
                }